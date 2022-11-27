package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Jph_PatchPojo {
    private Integer userId;
    private String title;
    private Boolean completed;
    private Integer id;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Boolean getCompleted() {
        return completed;
    }
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Jph_PatchPojo(Integer userId, String title, Boolean completed, Integer id) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
        this.id = id;
    }

    public Jph_PatchPojo() {
    }

    @Override
    public String toString() {
        return "Jph_PatchPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", id=" + id +
                '}';
    }

    public Map<String,Object> getAsMap() {
        Map<String,Object> map=new HashMap<>();
        map.put("userId",userId);
        map.put("id",id);
        map.put("completed",completed);
        map.put("title",title);
        return  map;
    }
}
