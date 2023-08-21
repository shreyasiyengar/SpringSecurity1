package com.security.DemoSecuritybasic.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Accounts {

    @Column(name ="customer_id")
    private int customerId;

    @Id
    @Column(name = "account_number")
    private long accountName;

    @Column(name="account_type")
    private String accoutntype;

    @Column(name="create_dt")
    private String createDt;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getAccountName() {
        return accountName;
    }

    public void setAccountName(long accountName) {
        this.accountName = accountName;
    }

    public String getAccoutntype() {
        return accoutntype;
    }

    public void setAccoutntype(String accoutntype) {
        this.accoutntype = accoutntype;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }
}
