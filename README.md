# abilitytest

先读笔记：

1.由于系统加入了登录认证，所以还没有登录访问非登录页面或调用非登录接口都会被控制跳转到登录页面。
因此，请使用者先在登录页面进行登录或者在test页面调用登录接口进行登录。

2.为区别正常页面和测试接口页面，现修改测试接口页面为：主机号:端口号/AbilityTest/interfacetest/test，
例：localhost:8080/AbilityTest/interfacetest/test

3.添加了新页面后，想进入该页面必须在com.abilitytest.action. IndexAction类下模仿其他写法来设置入口

4.选填表示可以不加入该参数





--------------------------------------------------------------------------------------------------------------------------------
<ul>
    <li>url：/AbilityTest/user/login</li>
    <li>type：post</li>
    <li>description：登录系统接口</li>
</ul>
<table>
    <tr>
        <th>传参名</th><th>类型</th><th>意义</th><th>是否必须</th><th>例子</th>
    </tr>
    <tr>
        <td>account</td><td>String</td><td>账号</td><td>是</td><td>“kk”</td>
    </tr>
    <tr>
        <td>password</td><td>String</td><td>密码</td><td>是</td><td>“123456”</td>
    </tr>
</table>
<table>
    <tr>
        <th>返回参数名</th><th>类型</th><th>意义</th><th>例子</th>
    </tr>
    <tr>
        <td>status</td><td>int</td><td>状态码：0/1/2</td><td>0</td>
    </tr>
    <tr>
        <td>msg</td><td>String</td><td>信息：“success”/“no this account”/“password is error”/“success”</td><td>“success”</td>
    </tr>
    <tr>
        <td>total</td><td>int</td><td>总条数</td><td>1</td>
    </tr>
    <tr>
        <td>type</td><td>int</td><td>类型：0--系统管理员，1--测试对象</td><td>0</td>
    </tr>
</table>






-------------------------------------------------------------------------------------------------------------------------------
<ul>
    <li>url：/AbilityTest/user/regist</li>
    <li>type：post</li>
    <li>description：注册系统接口</li>
</ul>
<table>
    <tr>
        <th>传参名</th><th>类型</th><th>意义</th><th>是否必须</th><th>例子</th>
    </tr>
    <tr>
        <td>name</td><td>String</td><td>残疾人姓名</td><td>是</td><td>“kk”</td>
    </tr>
    <tr>
        <td>phone</td><td>String</td><td>联系电话</td><td>是</td><td>“123456”</td>
    </tr>
    <tr>
        <td>id_number</td><td>String</td><td>证件号码</td><td>是</td><td>“123456”</td>
    </tr>
    <tr>
        <td>sex</td><td>int</td><td>性别</td><td>是</td><td>“123456”</td>
    </tr>
    <tr>
        <td>disability_type</td><td>int</td><td>残疾类型：0--肢体残疾<br>
                                                  1--听力残疾<br>
                                                   2--智力残疾<br>
                                                   3--视力残疾</td><td>是</td><td>0</td>
    </tr>
</table>
<table>
    <tr>
        <th>传参名</th><th>类型</th><th>意义</th><th>例子</th>
    </tr>
    <tr>
        <td>status</td><td>int</td><td>状态码：0/1/2</td><td>2</td>
    </tr>
    <tr>
        <td>msg</td><td>String</td><td>信息：“success!But user is not exist!”/“success!No Submission!”/” success!Have Submission!”	“success!Have Submission!”</td>
        <td>“success!Have Submission!”</td>
    </tr>
    <tr>
        <td>total</td><td>int</td><td>总条数</td><td>0</td>
    </tr>
    <tr>
        <td>personid</td><td>int</td><td>残疾人id</td><td>1</td>
    </tr>
</table>



--------------------------------------------------------------------------------------------------------------------------------------------------------------

url：/AbilityTest/user/ loadLastResult
type：get

description：登录系统接口

传参名	类型	意义	是否必须	例子
person_id	int	残疾人id	是	“1”

返回参数名	类型	意义	例子
status	int	状态码：0/1	0
msg	String	信息：“success”/“this person_id has not result!”	“success”
total	int	总条数	1
test	String	测试答题情况，每一部分有答题：1/无答题：0。以'',''分割开	‘1,0,0,1,0,1,1,0,1’



--------------------------------------------------------------------------------------------------------------------------------------------------------------

url：/AbilityTest/test/ getTestList
type：post
description：获取测试结果列表
传参名	类型	意义	是否必须	例子
name	String	用户名 	选填	“kk”
id_number	String	证件号 	选填	“1”
startTime	String	开始时间,格式为yyyy-mm-dd(表示该日的零时零分)	选填	“2018-01-19”
endTime	String	结束时间,格式为yyyy-mm-dd(表示该日的零时零分)	选填	“2018-01-20”
pageNum	int	页号，默认1	选填	1
pageSize	int	页面大小，默认10	选填	10

返回参数名	类型	意义	例子
status	int	状态码：0	0
msg	String	信息：“success”	“success”
total	int	总条数	5
name	String	姓名	‘kk’
id_number	String	残疾证件号	‘1’
modifytime
String	最后一次修改时间	"2018-01-18 16:45:08"

testid	int	测试id	1
personid	int	残疾人id	1

注：选填表示可以不加入该参数



--------------------------------------------------------------------------------------------------------------------------------------------------------------
url：/AbilityTest/user/ checkAccountExist
type：get
description：检查该账号是否存在
传参名	类型	意义	是否必须	例子
account	String	账号	是	“kk”

返回参数名	类型	意义	例子
status	int	状态码：0/1	0
msg	String	信息：“account is exist”/“no this account”	“success”
total	int	总条数	0



--------------------------------------------------------------------------------------------------------------------------------------------------------------
url：/AbilityTest/user/addAdministrator
type：post
description：添加管理员接口
传参名	类型	意义	是否必须	例子
account	String	账号	是	“kk”
password	String	密码	是	“123456”
type	int	类型：0--系统管理员，1--普通管理员，默认1	选填	1

返回参数名	类型	意义	例子
status	int	状态码：0/1/2	0
msg	String	信息：“success”
“this account exist”
“inside error”	“success”
total	int	总条数	0



--------------------------------------------------------------------------------------------------------------------------------------------------------------
url：/AbilityTest/user/updatePsw
type：post
description：更新密码接口
传参名	类型	意义	是否必须	例子
account	String	账号	是	“kk”
password	String	密码	是	“123456”

返回参数名	类型	意义	例子
status	int	状态码：0/1	0
msg	String	信息：“success”/“no this account”	“success”
total	int	总条数	0


--------------------------------------------------------------------------------------------------------------------------------------------------------------

url：/AbilityTest/test/ getAllResults
type：get
description：获取某次测试的测试结果
传参名	类型	意义	是否必须	例子
testid	int	测试id 	是	1

返回参数名	类型	意义	例子
status	int	状态码：0	0
msg	String	信息：“success”	“success”
total	int	总条数	5
testNumber	int	测试号码	1
name	String	姓名	‘kk’
id_number	String	残疾证件号	‘1’
answer	String	该部分24题的答案，以’,’分割开，没有答用-1表示	‘A,B,B,B,B,B,B,C,C,D,A,C,B
,D,B,C,A,B,D,B,A,C,B,D‘

usetime	time	该部分答题所用时间	05:46:41
finishtime	datetime	该部分完成时间	2018-01-21 10:46:25

