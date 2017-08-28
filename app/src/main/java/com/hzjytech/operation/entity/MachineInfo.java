package com.hzjytech.operation.entity;

import java.util.List;

/**
 * Created by hehongcan on 2017/4/26.
 */
public class MachineInfo {

    /**
     * auth : ["1.0","1.1","1.2","1.3","1.4","1.5"]
     * staffInfo : [{"userId":7,"roleId":8,"userName":"yuqifeng-jialiao","roleName":"加盟商"},{"userId":25,"roleId":8,"userName":"郑昊","roleName":"加盟商"},{"userId":22,"roleId":5,"userName":"yun ","roleName":"品质主管"},{"userId":32,"roleId":1,"userName":"沈杰","roleName":"管理员"},{"userId":8,"roleId":1,"userName":"sulili","roleName":"管理员"},{"userId":27,"roleId":1,"userName":"张玲","roleName":"管理员"},{"userId":4,"roleId":1,"userName":"maotianyi","roleName":"管理员"},{"userId":20,"roleId":5,"userName":"chengmingwei","roleName":"品质主管"},{"userId":30,"roleId":1,"userName":"李志明","roleName":"管理员"},{"userId":6,"roleId":4,"userName":"yuqifeng","roleName":"维修师"},{"userId":5,"roleId":1,"userName":"波波","roleName":"管理员"}]
     * alert : [{"materialName":"糖","materialId":3,"materialType":"Material","transFactor":1.1,"alertValue":111,"magazineNumber":4},{"materialName":"奶粉","materialId":16,"materialType":"Material","transFactor":1.5,"alertValue":111,"magazineNumber":3},{"materialName":"巧克力粉","materialId":6,"materialType":"Material","transFactor":1.3,"alertValue":111,"magazineNumber":2},{"materialName":"咖啡豆","materialId":4,"materialType":"Material","transFactor":1.1,"alertValue":115,"magazineNumber":9},{"materialName":"奶茶","materialId":8,"materialType":"Material","transFactor":1.1,"alertValue":0,"magazineNumber":1},{"materialName":"水","materialId":1,"materialType":"Material","transFactor":2,"alertValue":1111,"magazineNumber":-1},{"materialName":"杯子","materialId":2,"materialType":"Material","transFactor":1,"alertValue":111,"magazineNumber":-1},{"materialName":"椰子粉","materialId":10,"materialType":"Material","transFactor":1.1,"alertValue":111,"magazineNumber":5}]
     * shelf : [{"materialName":"红茶","materialId":23,"email":0,"status":1,"shelfDays":5,"lastSet":"2016-11-09 16:10:32","magazineNumber":1},{"materialName":"糖","materialId":3,"email":0,"status":0,"lastSet":"2016-06-02 15:58:59","magazineNumber":4},{"materialName":"奶粉","materialId":16,"email":0,"status":0,"lastSet":"2016-06-02 15:58:59","magazineNumber":3},{"materialName":"巧克力粉","materialId":6,"email":0,"status":0,"lastSet":"2016-06-02 15:58:59","magazineNumber":2},{"materialName":"咖啡豆","materialId":4,"email":0,"status":0,"lastSet":"2016-06-02 15:58:59","magazineNumber":9},{"materialName":"奶茶","materialId":8,"email":0,"status":0,"lastSet":"2016-06-02 15:58:59","magazineNumber":1},{"materialName":"水","materialId":1,"email":0,"status":0,"lastSet":"2016-11-09 16:10:32","magazineNumber":-1},{"materialName":"杯子","materialId":2,"email":0,"status":0,"lastSet":"2016-06-02 15:58:59","magazineNumber":-1},{"materialName":"椰子粉","materialId":10,"email":0,"status":0,"lastSet":"","magazineNumber":5}]
     * basicInfo : {"region":"江干区","groupName":"技术部测试","phone":"17767181630","operationStatus":false,"isLocked":false,"menuId":6,"beginTime":"2015-06-29 00:00:00","version":"4.3.3","country":"中国","city":"杭州市","typeName":"jijia-3","groupId":6,"address":"江干区下沙街道规划支路580号","magazineNum":4,"franchiseeName":"pctest","province":"浙江省","brand":"极伽","franchiseeId":14,"capCaliber":80,"longitude":"120.321782","latitude":"30.302911","typeId":4,"note":"1.1.1","beansWeight":8.5,"menuName":"'技术部测试'组菜单","paymentMethod":[0,1,0]}
     * otherInfo : {"keepTemperature":95,"workingTemperature":98,"washTime":["12:00","13:20"]}
     * addMaterial : {"maintainDays":5,"basisDays":7,"status":true,"beginTime":"2016-08-11 00:00:00","record":[{"need":35,"aver":5,"materialId":13,"materialName":"糖","bag":21,"number":2}]}
     * consume : ["2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:14","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:23","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:32","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:34","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:35","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:17:37","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:12","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-08-11 09:54:19","2016-09-09 12:44:00","2016-09-09 12:44:00","2016-09-09 12:44:00","2016-09-09 12:44:00","2016-09-09 12:44:00","2016-09-09 12:44:00","2016-09-09 12:44:00","2016-09-09 12:44:00"]
     */

    private BasicInfoBean basicInfo;
    private OtherInfoBean otherInfo;
    private AddMaterialBean addMaterial;
    private List<String> auth;
    private List<StaffInfoBean> staffInfo;
    private List<AlertBean> alert;
    private List<ShelfBean> shelf;
    private List<String> consume;

    public BasicInfoBean getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfoBean basicInfo) {
        this.basicInfo = basicInfo;
    }

    public OtherInfoBean getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfoBean otherInfo) {
        this.otherInfo = otherInfo;
    }

    public AddMaterialBean getAddMaterial() {
        return addMaterial;
    }

    public void setAddMaterial(AddMaterialBean addMaterial) {
        this.addMaterial = addMaterial;
    }

    public List<String> getAuth() {
        return auth;
    }

    public void setAuth(List<String> auth) {
        this.auth = auth;
    }

    public List<StaffInfoBean> getStaffInfo() {
        return staffInfo;
    }

    public void setStaffInfo(List<StaffInfoBean> staffInfo) {
        this.staffInfo = staffInfo;
    }

    public List<AlertBean> getAlert() {
        return alert;
    }

    public void setAlert(List<AlertBean> alert) {
        this.alert = alert;
    }

    public List<ShelfBean> getShelf() {
        return shelf;
    }

    public void setShelf(List<ShelfBean> shelf) {
        this.shelf = shelf;
    }

    public List<String> getConsume() {
        return consume;
    }

    public void setConsume(List<String> consume) {
        this.consume = consume;
    }

    public static class BasicInfoBean {
        /**
         * region : 江干区
         * groupName : 技术部测试
         * phone : 17767181630
         * operationStatus : false
         * isLocked : false
         * menuId : 6
         * beginTime : 2015-06-29 00:00:00
         * version : 4.3.3
         * country : 中国
         * city : 杭州市
         * typeName : jijia-3
         * groupId : 6
         * address : 江干区下沙街道规划支路580号
         * magazineNum : 4
         * franchiseeName : pctest
         * province : 浙江省
         * brand : 极伽
         * franchiseeId : 14
         * capCaliber : 80
         * longitude : 120.321782
         * latitude : 30.302911
         * typeId : 4
         * note : 1.1.1
         * beansWeight : 8.5
         * menuName : '技术部测试'组菜单
         * paymentMethod : [0,1,0]
         * isSupportCoffeeMe
         * openingTime
         * closingTime
         */

        private String region;
        private String groupName;
        private String phone;
        private boolean operationStatus;
        private boolean isLocked;
        private int menuId;
        private String name;
        private String beginTime;
        private String version;
        private String country;
        private String city;
        private String typeName;
        private int groupId;
        private String address;
        private int magazineNum;
        private String franchiseeName;
        private String province;
        private String brand;
        private int franchiseeId;
        private double capCaliber;
        private String longitude;
        private String latitude;
        private int typeId;
        private String note;
        private double beansWeight;
        private String menuName;
        private boolean isSupportCoffeeMe;
        private String openingTime;
        private String closingTime;

        public boolean isLocked() {
            return isLocked;
        }

        public void setLocked(boolean locked) {
            isLocked = locked;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private List<Integer> paymentMethod;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getGroupName() {
            return groupName;
        }

        public void setGroupName(String groupName) {
            this.groupName = groupName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public boolean isOperationStatus() {
            return operationStatus;
        }

        public void setOperationStatus(boolean operationStatus) {
            this.operationStatus = operationStatus;
        }

        public boolean isIsLocked() {
            return isLocked;
        }

        public void setIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
        }

        public int getMenuId() {
            return menuId;
        }

        public void setMenuId(int menuId) {
            this.menuId = menuId;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public int getGroupId() {
            return groupId;
        }

        public void setGroupId(int groupId) {
            this.groupId = groupId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getMagazineNum() {
            return magazineNum;
        }

        public void setMagazineNum(int magazineNum) {
            this.magazineNum = magazineNum;
        }

        public String getFranchiseeName() {
            return franchiseeName;
        }

        public void setFranchiseeName(String franchiseeName) {
            this.franchiseeName = franchiseeName;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getFranchiseeId() {
            return franchiseeId;
        }

        public void setFranchiseeId(int franchiseeId) {
            this.franchiseeId = franchiseeId;
        }

        public double getCapCaliber() {
            return capCaliber;
        }

        public void setCapCaliber(double capCaliber) {
            this.capCaliber = capCaliber;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public double getBeansWeight() {
            return beansWeight;
        }

        public void setBeansWeight(double beansWeight) {
            this.beansWeight = beansWeight;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public List<Integer> getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(List<Integer> paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public boolean isSupportCoffeeMe() {
            return isSupportCoffeeMe;
        }

        public void setSupportCoffeeMe(boolean supportCoffeeMe) {
            isSupportCoffeeMe = supportCoffeeMe;
        }

        public String getOpeningTime() {
            return openingTime;
        }

        public void setOpeningTime(String openingTime) {
            this.openingTime = openingTime;
        }

        public String getClosingTime() {
            return closingTime;
        }

        public void setClosingTime(String closingTime) {
            this.closingTime = closingTime;
        }
    }

    public static class OtherInfoBean {
        /**
         * keepTemperature : 95
         * workingTemperature : 98
         * washTime : ["12:00","13:20"]
         */

        private int keepTemperature;
        private int workingTemperature;
        private Object washTime;

        public int getKeepTemperature() {
            return keepTemperature;
        }

        public void setKeepTemperature(int keepTemperature) {
            this.keepTemperature = keepTemperature;
        }

        public int getWorkingTemperature() {
            return workingTemperature;
        }

        public void setWorkingTemperature(int workingTemperature) {
            this.workingTemperature = workingTemperature;
        }

        public Object getWashTime() {
            return washTime;
        }

        public void setWashTime(Object washTime) {
            this.washTime = washTime;
        }
    }

    public static class AddMaterialBean {
        /**
         * maintainDays : 5
         * basisDays : 7
         * status : true
         * beginTime : 2016-08-11 00:00:00
         * record : [{"need":35,"aver":5,"materialId":13,"materialName":"糖","bag":21,"number":2}]
         */

        private int maintainDays;
        private int basisDays;
        private boolean status;
        private String beginTime;
        private List<RecordBean> record;

        public int getMaintainDays() {
            return maintainDays;
        }

        public void setMaintainDays(int maintainDays) {
            this.maintainDays = maintainDays;
        }

        public int getBasisDays() {
            return basisDays;
        }

        public void setBasisDays(int basisDays) {
            this.basisDays = basisDays;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public String getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(String beginTime) {
            this.beginTime = beginTime;
        }

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class RecordBean {
            /**
             * need : 35
             * aver : 5
             * materialId : 13
             * materialName : 糖
             * bag : 21
             * number : 2
             */

            private int need;
            private int aver;
            private int materialId;
            private String materialName;
            private int bag;
            private int number;

            public int getNeed() {
                return need;
            }

            public void setNeed(int need) {
                this.need = need;
            }

            public int getAver() {
                return aver;
            }

            public void setAver(int aver) {
                this.aver = aver;
            }

            public int getMaterialId() {
                return materialId;
            }

            public void setMaterialId(int materialId) {
                this.materialId = materialId;
            }

            public String getMaterialName() {
                return materialName;
            }

            public void setMaterialName(String materialName) {
                this.materialName = materialName;
            }

            public int getBag() {
                return bag;
            }

            public void setBag(int bag) {
                this.bag = bag;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            @Override
            public String toString() {
                return "RecordBean{" +
                        "need=" + need +
                        ", aver=" + aver +
                        ", materialId=" + materialId +
                        ", materialName='" + materialName + '\'' +
                        ", bag=" + bag +
                        ", number=" + number +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "AddMaterialBean{" +
                    "maintainDays=" + maintainDays +
                    ", basisDays=" + basisDays +
                    ", status=" + status +
                    ", beginTime='" + beginTime + '\'' +
                    ", record=" + record +
                    '}';
        }
    }

    public static class StaffInfoBean {
        /**
         * userId : 7
         * roleId : 8
         * userName : yuqifeng-jialiao
         * roleName : 加盟商
         */

        private int userId;
        private int roleId;
        private String userName;
        private String roleName;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getRoleId() {
            return roleId;
        }

        public void setRoleId(int roleId) {
            this.roleId = roleId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        @Override
        public String toString() {
            return "StaffInfoBean{" +
                    "userId=" + userId +
                    ", roleId=" + roleId +
                    ", userName='" + userName + '\'' +
                    ", roleName='" + roleName + '\'' +
                    '}';
        }
    }

    public static class AlertBean {
        /**
         * materialName : 糖
         * materialId : 3
         * materialType : Material
         * transFactor : 1.1
         * alertValue : 111
         * magazineNumber : 4
         */

        private String materialName;
        private int materialId;
        private String materialType;
        private double transFactor;
        private int alertValue;
        private int magazineNumber;

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        public String getMaterialType() {
            return materialType;
        }

        public void setMaterialType(String materialType) {
            this.materialType = materialType;
        }

        public double getTransFactor() {
            return transFactor;
        }

        public void setTransFactor(double transFactor) {
            this.transFactor = transFactor;
        }

        public int getAlertValue() {
            return alertValue;
        }

        public void setAlertValue(int alertValue) {
            this.alertValue = alertValue;
        }

        public int getMagazineNumber() {
            return magazineNumber;
        }

        public void setMagazineNumber(int magazineNumber) {
            this.magazineNumber = magazineNumber;
        }

        @Override
        public String toString() {
            return "AlertBean{" +
                    "materialName='" + materialName + '\'' +
                    ", materialId=" + materialId +
                    ", materialType='" + materialType + '\'' +
                    ", transFactor=" + transFactor +
                    ", alertValue=" + alertValue +
                    ", magazineNumber=" + magazineNumber +
                    '}';
        }
    }

    public static class ShelfBean {
        /**
         * materialName : 红茶
         * materialId : 23
         * email : 0
         * status : 1
         * shelfDays : 5
         * lastSet : 2016-11-09 16:10:32
         * magazineNumber : 1
         */

        private String materialName;
        private int materialId;
        private int email;
        private int status;
        private int shelfDays;
        private String lastSet;
        private int magazineNumber;

        public String getMaterialName() {
            return materialName;
        }

        public void setMaterialName(String materialName) {
            this.materialName = materialName;
        }

        public int getMaterialId() {
            return materialId;
        }

        public void setMaterialId(int materialId) {
            this.materialId = materialId;
        }

        public int getEmail() {
            return email;
        }

        public void setEmail(int email) {
            this.email = email;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getShelfDays() {
            return shelfDays;
        }

        public void setShelfDays(int shelfDays) {
            this.shelfDays = shelfDays;
        }

        public String getLastSet() {
            return lastSet;
        }

        public void setLastSet(String lastSet) {
            this.lastSet = lastSet;
        }

        public int getMagazineNumber() {
            return magazineNumber;
        }

        public void setMagazineNumber(int magazineNumber) {
            this.magazineNumber = magazineNumber;
        }

        @Override
        public String toString() {
            return "ShelfBean{" +
                    "materialName='" + materialName + '\'' +
                    ", materialId=" + materialId +
                    ", email=" + email +
                    ", status=" + status +
                    ", shelfDays=" + shelfDays +
                    ", lastSet='" + lastSet + '\'' +
                    ", magazineNumber=" + magazineNumber +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MachieInfo{" +
                "basicInfo=" + basicInfo +
                ", otherInfo=" + otherInfo +
                ", addMaterial=" + addMaterial +
                ", auth=" + auth +
                ", staffInfo=" + staffInfo +
                ", alert=" + alert +
                ", shelf=" + shelf +
                ", consume=" + consume +
                '}';
    }
}
