package com.hzjytech.operation.http.api;

import com.hzjytech.operation.entity.AddMaterialInfo;
import com.hzjytech.operation.entity.CurrentDataInfo;
import com.hzjytech.operation.entity.DailyOrderInfo;
import com.hzjytech.operation.entity.DetailSaleMachine;
import com.hzjytech.operation.entity.DetailTaskInfo;
import com.hzjytech.operation.entity.ErrorHistory;
import com.hzjytech.operation.entity.GroupInfo;
import com.hzjytech.operation.entity.GroupList;
import com.hzjytech.operation.entity.Groups;
import com.hzjytech.operation.entity.LoginInfo;
import com.hzjytech.operation.entity.Machies;
import com.hzjytech.operation.entity.MachineInfo;
import com.hzjytech.operation.entity.MachineList;
import com.hzjytech.operation.entity.MenuInfo;
import com.hzjytech.operation.entity.MenuList;
import com.hzjytech.operation.entity.PersonInfo;
import com.hzjytech.operation.entity.PollingInfo;
import com.hzjytech.operation.entity.RepeatInfo;
import com.hzjytech.operation.entity.ScanInfo;
import com.hzjytech.operation.entity.TaskList;
import com.hzjytech.operation.entity.TaskListInfo;
import com.hzjytech.operation.entity.UpdateInfo;
import com.hzjytech.operation.entity.User;
import com.hzjytech.operation.entity.UserCommentInfo;
import com.hzjytech.operation.entity.WasteMaterialInfo;
import com.hzjytech.operation.entity.WeeklySaleInfo;
import com.hzjytech.operation.http.HttpResult;
import com.hzjytech.operation.http.NetConstants;


import org.joda.time.DateTime;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiInterface {
 /** RxJava形式 */

 /** userService */
 //用户登录
 @POST("admins/login")
 Observable<HttpResult<LoginInfo>> login(
         @Query(NetConstants.LOGIN) String login,
         @Query(NetConstants.PASSWORD) String password,
         @Query(NetConstants.LINE) String line
 );
 //获取用户信息
 @GET("admins/personalData")
 Observable<HttpResult<User>> getPersonalData(@Query(NetConstants.TOKEN) String token);
 //修改电话号码
 @POST("admins/modifyInfo")
 Observable<HttpResult<Object>>changePhone(
         @Query(NetConstants.ADMIN_ID)int adminId,
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.PHONE)String phone
 );
 //修改密码
 @POST("admins/changePassword")
 Observable<HttpResult<Object>>changePassword(
         @Query(NetConstants.ADMIN_ID)int adminId,
         @Query(NetConstants.PASSWORD)String password,
         @Query(NetConstants.NEWPASSWORD)String newPassword,
         @Query(NetConstants.TOKEN)String token
 );
 //获取咖啡机列表
 @GET("vendingMachines/list")
 Observable<HttpResult<Machies>>getMachines(
         @Query(NetConstants.STATUS)String queryStatus,
         @Query(NetConstants.QUERY_CONDITION)String queryCondition,
         @Query(NetConstants.PAGESIZE)int pageSize,
         @Query(NetConstants.PAGENUMBER)int pageNumber,
         @Query(NetConstants.TOKEN)String token
 );
 //获取单台机器的具体信息
 @GET("vendingMachines/info")
 Observable<HttpResult<MachineInfo>>getSingleMachieDetail(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.MACHINEID)int machineid
 );
 //获取咖啡机历时错误记录
 @GET("vendingMachines/errorList")
 Observable<HttpResult<ErrorHistory>>getErrorHistory(
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.MACHINEIDS) ArrayList<Integer> ids,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.TOKEN) String token,
         @Query(NetConstants.PAGENO)int pageNumber,
         @Query(NetConstants.PAGESIZE)int pageSize
 );
 //获取咖啡机组信息
 @GET("groups/info")
 Observable<HttpResult<GroupInfo>>getGroupInfo(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.GROUPID)int groupId
 );
 //获取菜单信息
 @GET("menus/info")
 Observable<HttpResult<MenuInfo>>getMenuInfo(
   @Query(NetConstants.TOKEN)String token,
   @Query(NetConstants.MENUID)int menuId
 );
 //获取单台管理列表
 @GET("vendingMachines/machineList")
 Observable<HttpResult<MachineList>>getSingleMachineList(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.QUERY_CONDITION)String queryCondition,
         @Query(NetConstants.PAGENUMBER)int pageNumber,
         @Query(NetConstants.PAGESIZE)int pageSize
 );
 //获取机器组管理列表
 @GET("groups/list")
 Observable<HttpResult<List<GroupList>>>getSingleGroupList(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.CONDITION)String queryCondition
 );
//获取菜单列表
@GET("menus/list")
 Observable<HttpResult<List<MenuList>>> getSingleMenuList(
        @Query(NetConstants.TOKEN)String token,
        @Query(NetConstants.CONDITION)String queryCondition
);
 //获取当前数据统计
 @GET("orders/currentData")
 Observable<HttpResult<CurrentDataInfo>>getCurrentData(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)String startTime,
         @Query(NetConstants.ENDTIME)String endTime
 );
 //获取提供选择的咖啡机列表
 @GET("groups/groupList")
 Observable<HttpResult<List<Groups>>>getSelectMachines(
         @Query(NetConstants.TOKEN)String token
 );
 //获取咖啡机销售统计表
 @GET("orders/salesData")
 Observable<HttpResult<DetailSaleMachine>>getDetailSaleMachine(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list
 );
 //每日销售统计
 @GET("orders/dailyData")
 Observable<HttpResult<Object>>getDailyData(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list
 );
 //加料统计
 @GET("materials/chargeRecord")
 Observable<HttpResult<AddMaterialInfo>>getAddMaterial(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list,
         @Query(NetConstants.PAGENO)int pageNo,
         @Query(NetConstants.PAGESIZE)int pageSize
 );
@GET("materials/consumption")
 Observable<HttpResult<WasteMaterialInfo>>getWasteMaterial(
        @Query(NetConstants.TOKEN)String token,
        @Query(NetConstants.STARTTIME)long startTime,
        @Query(NetConstants.ENDTIME)long endTime,
        @Query(NetConstants.NEARLY)int nearly,
        @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list
);
 @GET("task/getDutyInfo")
 Observable<HttpResult<List<PersonInfo>>>getPersonList(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.MACHINEID)int id
 );
@GET("task/getTaskList")
 Observable<HttpResult<TaskList>>getTaskList(
        @Query(NetConstants.TOKEN)String token,
        @Query(NetConstants.ISMINE)boolean isMine,
        @Query(NetConstants.STATU)int status,
        @Query(NetConstants.KEYWORD)String keyWord,
        @Query(NetConstants.PAGE)int page,
        @Query(NetConstants.PAGESIZE)int pageSize
);
 @POST("task/createTask")
 Observable<HttpResult<Boolean>>createTask(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.MACHINEID)int machineId,
         @Query(NetConstants.DUTYIDS)ArrayList<Integer>dutyIds,
         @Query(NetConstants.COMMENT)String comment,
         @Query(NetConstants.URL)ArrayList<String>urls,
         @Query(NetConstants.TYPE)int type,
         @Query(NetConstants.CUTOFFTIME)String cutOffTime
 );
 @GET("task/getTaskInfo")
 Observable<HttpResult<DetailTaskInfo>>getTaskInfo(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.TASKID)int taskId
 );
 @POST("task/addTaskComment")
 Observable<HttpResult<Boolean>>addComment(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.COMMENT)String comment,
         @Query(NetConstants.URL)ArrayList<String>urls,
         @Query(NetConstants.TASKID)int taskId
 );
 @POST("task/changeTaskStatus")
 Observable<HttpResult<Boolean>>changeTaskStatus(
         @Query(NetConstants.TOKEN)String token,
        @Query(NetConstants.STATU)int status,
         @Query(NetConstants.TASKID)int taskId
 );
 @POST("maker/loginMachine")
 Observable<HttpResult<ScanInfo>>loginMachine(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.MACHINEID)int machineId,
         @Query(NetConstants.ISLOGIN)boolean isLogin,
         @Query(NetConstants.QCCODE)String QCCode,
         @Query(NetConstants.RECORDID)Integer recordId
 );
 @GET("task/getAppInfo")
 Observable<HttpResult<UpdateInfo>>getAppInfo(
         @Query(NetConstants.TOKEN)String token
 );
 @GET("maker/getMachineIdByQRCode")
 Observable<HttpResult<String>>getMachineIdByQRCode(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.QCCODE)String QRCode
 );
 @GET("inspection/getTitleAndContent")
 Observable<HttpResult<List<PollingInfo>>>getPollingContent(
         @Query(NetConstants.TOKEN)String token
 );
 @POST("inspection/addInfo")
 Observable<HttpResult<Boolean>>addInfo(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.MACHINEID)int machineId,
         @Query(NetConstants.FINISHCONTENT)String  finishContent,
         @Query(NetConstants.UNFINISHCONTENT)String unfinishContent
 );
 //每日销售统计
 @GET("orders/dailyData")
 Observable<HttpResult<List<DailyOrderInfo>>>getNewDailyData(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list
 );
 //一周销售统计
 @GET("orders/chartData")
 Observable<HttpResult<Object>>getWeeklyData(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list
 );
 //修改任务截止时间
 @POST("task/changeCutOffTime")
 Observable<HttpResult<Boolean>>changeCutOffTime(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.TASKID)int taskId,
         @Query(NetConstants.CUTOFFTIME)String cutOffTime
 );
 //修改任务联系人
 @POST("task/changeTaskDutyNames")
 Observable<HttpResult<Boolean>>changeTaskDutyNames(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.TASKID)int taskId,
         @Query(NetConstants.DUTYIDS)int dutyIds,
         @Query(NetConstants.ADDORSUBTRACT)boolean addOrSubtract
 );
//用户评价
 @GET("vendingMachines/getUserEvaluationRate")
    Observable<HttpResult<UserCommentInfo>> getUserComment(
         @Query(NetConstants.TOKEN)String token,
         @Query(NetConstants.STARTTIME)long startTime,
         @Query(NetConstants.ENDTIME)long endTime,
         @Query(NetConstants.NEARLY)int nearly,
         @Query(NetConstants.MACHINEIDS)ArrayList<Integer> list
 );
}
