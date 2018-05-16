import java.util.Objects;


public class AllowanceBean {
    private int allownceId;
    private String idNum;
    private String beginTime;
    private String lastTime;
    private int sumMoney;
    private Integer lastMoney;
    private int monthes;
    private String allowancetype;
    private String updatetime;
    private String shebao;
    private String bank;
    private String bankCard;
    private String phone;
    private String name;
    private String company;
    private String isfirstschool;
    private String batch;


    public int getAllownceId() {
        return allownceId;
    }

    public void setAllownceId(int allownceId) {
        this.allownceId = allownceId;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }


    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }


    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(String lastTime) {
        this.lastTime = lastTime;
    }


    public int getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(int sumMoney) {
        this.sumMoney = sumMoney;
    }


    public Integer getLastMoney() {
        return lastMoney;
    }

    public void setLastMoney(Integer lastMoney) {
        this.lastMoney = lastMoney;
    }

    public int getMonthes() {
        return monthes;
    }

    public void setMonthes(int monthes) {
        this.monthes = monthes;
    }


    public String getAllowancetype() {
        return allowancetype;
    }

    public void setAllowancetype(String allowancetype) {
        this.allowancetype = allowancetype;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getShebao() {
        return shebao;
    }

    public void setShebao(String shebao) {
        this.shebao = shebao;
    }


    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }


    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }


    public String getIsfirstschool() {
        return isfirstschool;
    }

    public void setIsfirstschool(String isfirstschool) {
        this.isfirstschool = isfirstschool;
    }


    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllowanceBean that = (AllowanceBean) o;
        return allownceId == that.allownceId &&
                sumMoney == that.sumMoney &&
                monthes == that.monthes &&
                Objects.equals(idNum, that.idNum) &&
                Objects.equals(beginTime, that.beginTime) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(lastMoney, that.lastMoney) &&
                Objects.equals(allowancetype, that.allowancetype) &&
                Objects.equals(updatetime, that.updatetime) &&
                Objects.equals(shebao, that.shebao) &&
                Objects.equals(bank, that.bank) &&
                Objects.equals(bankCard, that.bankCard) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(name, that.name) &&
                Objects.equals(company, that.company) &&
                Objects.equals(isfirstschool, that.isfirstschool) &&
                Objects.equals(batch, that.batch);
    }

    @Override
    public int hashCode() {

        return Objects.hash(allownceId, idNum, beginTime, lastTime, sumMoney, lastMoney, monthes, allowancetype, updatetime, shebao, bank, bankCard, phone, name, company, isfirstschool, batch);
    }
}
