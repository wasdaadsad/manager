package com.vivo.uhost.core.domain.bo;

import com.vivo.uhost.dal.entity.ToolVersion;
import org.springframework.format.datetime.DateFormatter;

import java.util.Date;
import java.util.Locale;

/**
 * @author litingfa  2018/3/26 19:58
 * @version 1.0
 * @description
 */
public class ToolVersionBo {
    protected static String PATTEN = "yyyy-MM-dd HH:mm:ss";

    private Long id;
    private String versionName;
    private Integer toolId;
    private String toolUrl;
    private Integer state;
    private String stateVP;
    private Date createTime;
    private String createTimeVp;

    public ToolVersionBo(ToolVersion toolVersion) {
        this.id = toolVersion.getId();
        this.versionName = toolVersion.getVersionName();
        this.toolId = toolVersion.getToolId();
        this.toolUrl = toolVersion.getToolUrl();
        this.createTime = toolVersion.getCreateTime();
        this.state = toolVersion.getState();
        if(toolVersion.getCreateTime() != null){
            this.createTimeVp = new DateFormatter(PATTEN).print(toolVersion.getCreateTime(), Locale.CHINA);
        }
        if(toolVersion.getState() != null){
            switch (toolVersion.getState()){
                case 1:
                    stateVP = "有效";
                    break;
                case 0:
                    stateVP = "无效";
                    break;
                default:
                    stateVP = "未知";
            }
        }
    }

    public ToolVersion toEntity(){
        ToolVersion toolVersion = new ToolVersion();
        toolVersion.setId(this.id);
        toolVersion.setVersionName(this.versionName);
        toolVersion.setToolId(this.toolId);
        toolVersion.setToolUrl(this.toolUrl);
        toolVersion.setState(this.state);
        toolVersion.setCreateTime(this.createTime);
        return toolVersion;
    }

    public String getStateVP() {
        return stateVP;
    }

    public void setStateVP(String stateVP) {
        this.stateVP = stateVP;
    }

    public ToolVersionBo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    public String getToolUrl() {
        return toolUrl;
    }

    public void setToolUrl(String toolUrl) {
        this.toolUrl = toolUrl;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeVp() {
        return createTimeVp;
    }

    public void setCreateTimeVp(String createTimeVp) {
        this.createTimeVp = createTimeVp;
    }
}
