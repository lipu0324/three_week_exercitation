package com.sifu.sfcc.search.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
@Document(indexName = "xmk_tels", type = "xmkTels", shards = 1, replicas = 0)
public class XmkTels implements Serializable {
    @Id
    private Integer id;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String company;

    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String contacts;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String contactTel;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String contactPhone;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String dutyRoom;
    @Field(analyzer = "ik_smart", type = FieldType.Text)
    private String territory;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getDutyRoom() {
        return dutyRoom;
    }

    public void setDutyRoom(String dutyRoom) {
        this.dutyRoom = dutyRoom;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", company=").append(company);
        sb.append(", contacts=").append(contacts);
        sb.append(", contactTel=").append(contactTel);
        sb.append(", contactPhone=").append(contactPhone);
        sb.append(", dutyRoom=").append(dutyRoom);
        sb.append(", territory=").append(territory);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}