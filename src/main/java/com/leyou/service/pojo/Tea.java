package com.leyou.service.pojo;

import java.util.List;

public class Tea {

    private String teaId;
    private String teaNm;

    List<Sub> sub;

    public String getTeaId() {
        return teaId;
    }

    public void setTeaId(String teaId) {
        this.teaId = teaId;
    }

    public String getTeaNm() {
        return teaNm;
    }

    public void setTeaNm(String teaNm) {
        this.teaNm = teaNm;
    }

    public List<Sub> getSub() {
        return sub;
    }

    public void setSub(List<Sub> sub) {
        this.sub = sub;
    }
}
