package cn.sixlab.app.sixlabapp.bean;

public class SixlabMovie {
    private Integer id;
    private String movieName;
    private String produceYear;
    private String director;
    private String remark;
    private String viewDate;
    private Double doubanScore;
    private String doubanKey;

    private String doubanInfo;
    private String infoStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getProduceYear() {
        return produceYear;
    }

    public void setProduceYear(String produceYear) {
        this.produceYear = produceYear;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getViewDate() {
        return viewDate;
    }

    public void setViewDate(String viewDate) {
        this.viewDate = viewDate;
    }

    public String getDoubanKey() {
        return doubanKey;
    }

    public void setDoubanKey(String doubanKey) {
        this.doubanKey = doubanKey;
    }

    public Double getDoubanScore() {
        return doubanScore;
    }

    public void setDoubanScore(Double doubanScore) {
        this.doubanScore = doubanScore;
    }

    public String getDoubanInfo() {
        return doubanInfo;
    }

    public void setDoubanInfo(String doubanInfo) {
        this.doubanInfo = doubanInfo;
    }

    public String getInfoStatus() {
        return infoStatus;
    }

    public void setInfoStatus(String infoStatus) {
        this.infoStatus = infoStatus;
    }
}
