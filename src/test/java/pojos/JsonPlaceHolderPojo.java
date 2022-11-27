package pojos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonPlaceHolderPojo {
    /*
        {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
        }
     */

    private Integer userId;
    private String title;
    private Boolean complated;

    public JsonPlaceHolderPojo() { }
    public JsonPlaceHolderPojo(Integer pUserId,String pTitle, Boolean pComplated) {
        this.userId=pUserId;
        this.title=pTitle;
        this.complated=pComplated;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public Boolean isComplated() {
        return complated;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComplated(Boolean complated) {
        this.complated = complated;
    }

    @Override
    public String toString() {
        return "JsonPlaceHolderPojo{" +
                "userId=" + userId +
                ", title='" + title + '\'' +
                ", complated=" + complated +
                '}';
    }
}
