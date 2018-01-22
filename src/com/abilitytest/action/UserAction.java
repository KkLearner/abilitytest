package com.abilitytest.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abilitytest.common.ResultReturn;
import com.abilitytest.entity.Administrator;
import com.abilitytest.entity.Disabledman;
import com.abilitytest.entity.TestPool;
import com.abilitytest.service.AdministratorService;
import com.abilitytest.service.DisabledmanService;
import com.abilitytest.service.TestPoolService;
import com.abilitytest.service.TestResultService;

@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private DisabledmanService disabledmanService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private TestPoolService testPoolService;
	
	/**
	 * url:/user/login
	 * type:post
	 * @param map 必须包括account,password两个key
	 * @return map <br>
	 * status：状态码  0/1/2<br>
	 * msg：返回信息 "success"/"no this account"/"password is error"<br>
	 * total：总条数<br>
	 * results：type(类型：0--系统管理员，1--测试对象)<br>
	 */	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(HttpSession session,@RequestParam Map<String, Object>map){
		Administrator person = administratorService.findByUniqueProperty("account", (String)map.get("account"));
		if(person == null)
			return ResultReturn.setMap(1, "no this account", 0, null);
		else if(!person.getPassword().equals((String)map.get("password")))
			return ResultReturn.setMap(2, "password is error", 0, null);
		session.setAttribute("userid", person.getId());
		List<Map<String, Object>> results = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		result.put("type", person.getType());
		results.add(result);
		return ResultReturn.setMap(0, "success", 1, results);
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> regist(@RequestParam Map<String, Object>map){
		List<Disabledman> persons = disabledmanService.getByCriterion(Restrictions.eq("id_number", (String)map.get("id_number"))
				,Restrictions.eq("name", (String)map.get("name")));
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> teMap = new HashMap<>();
		if(persons!=null && !persons.isEmpty()){//已注册			
			teMap.put("personid", persons.get(0).getId());
			list.add(teMap);
			String hql = "select count(id) from TestPool where"
					+ " person_id="+persons.get(0).getId()+" and"
					+ " if_del=0";
			int number = testPoolService.countByHQL(hql);
			if(number==0)//没有提交测试
				return ResultReturn.setMap(1, "success!No Submission!", 0, list);			
		}else {//未注册，进行注册后直接进入测试页面
			Date now = new Date();
			Disabledman mDisabledman = new Disabledman((String)map.get("id_number")
					, (String)map.get("name"), Integer.parseInt((String)map.get("sex"))
					, (String)map.get("phone"), Integer.parseInt((String)map.get("disability_type"))
					, 0, now, now);
			disabledmanService.add(mDisabledman);
			teMap.put("personid", mDisabledman.getId());
			list.add(teMap);
			return ResultReturn.setMap(0, "success!But user is not exist!", 0, list);
		}
		return ResultReturn.setMap(2, "success!Have Submission!", 0, list);//已提交
	}
	
	@RequestMapping("/loadLastResult")
	@ResponseBody
	public Map<String, Object> loadLastResult(@RequestParam Map<String, Object>map){
		String sql = "select test from testpool"
				+ " where person_id="+Integer.parseInt((String)map.get("person_id"))
				+ " and if_del=0"
				+ " order by modifytime desc";
		List<Map<String, Object>> temp= testPoolService.getBySQL(sql);
		if(temp==null||temp.isEmpty())
			return ResultReturn.setMap(1, "this person_id has not result!", 0, null);
		List<Map<String, Object>> results = new ArrayList<>();
		results.add(temp.get(0));
		return ResultReturn.setMap(0, "success", 1, results);
	}
	
	@RequestMapping(value="/loginOut")
	@ResponseBody
	public Map<String, Object> loginOut(@RequestParam Map<String, Object>map,HttpSession session){
		session.invalidate();
		return ResultReturn.setMap(0, "success", 0, null);
	}
	
	@RequestMapping(value="/addAdministrator",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> adddministrator(@RequestParam Map<String, Object>map){	
		if(checkAccount((String)map.get("account")))
			return ResultReturn.setMap(1, "this account exist", 0, null);
		else if(administratorService.add(map))
			return ResultReturn.setMap(0, "success", 0, null);
		return ResultReturn.setMap(2, "inside error", 0, null);
	}
	
	public boolean checkAccount(String account) {
		return administratorService.findTotalByUniqueProperty("account", account)>0?true:false;
	}
	
	@RequestMapping(value="/checkAccountExist")
	@ResponseBody
	public Map<String, Object> checkAccountExist(@RequestParam(value="account",required=false,defaultValue="") String account){
		if(!account.equals("")&&checkAccount(account))
			return ResultReturn.setMap(0, "account is exist", 0, null);
		return ResultReturn.setMap(1, "no this account", 0, null);
	}
	
	@RequestMapping(value="/updatePsw",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updatePsw(@RequestParam(value="account") String account
			,@RequestParam(value="oldpassword") String oldpassword
			,@RequestParam(value="newpassword") String newpassword){
		Administrator person = administratorService.findByUniqueProperty("account", account);
		if(person == null)
			return ResultReturn.setMap(1, "no this account", 0, null);
		else if(!person.getPassword().equals(oldpassword))
			return ResultReturn.setMap(2, "password is error", 0, null);
		person.setPassword(newpassword);
		administratorService.update(person);
		return ResultReturn.setMap(0, "success", 0, null);	
	}
}
