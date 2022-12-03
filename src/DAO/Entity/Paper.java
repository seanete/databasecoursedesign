package DAO.Entity;

public class Paper {
    private String paperId;
    private String paperName;
    private String publicationName;
    private String paperStatus;
    private String publicationTime;
    private String indexType;
    private String attributionLibrary;
    private String pdf;

    public Paper(){
    }

    public Paper(String paperId, String paperName, String publicationName, String paperStatus, String publicationTime, String indexType, String attributionLibrary, String pdf) {
        this.paperId = paperId;
        this.paperName = paperName;
        this.publicationName = publicationName;
        this.paperStatus = paperStatus;
        this.publicationTime = publicationTime;
        this.indexType = indexType;
        this.attributionLibrary = attributionLibrary;
        this.pdf = pdf;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName;
    }

    public String getPaperStatus() {
        return paperStatus;
    }

    public void setPaperStatus(String paperStatus) {
        this.paperStatus = paperStatus;
    }

    public String getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(String publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getAttributionLibrary() {
        return attributionLibrary;
    }

    public void setAttributionLibrary(String attributionLibrary) {
        this.attributionLibrary = attributionLibrary;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    @Override
    public String toString() {
        return "Paper{" +
                "paperId='" + paperId + '\'' +
                ", paperName='" + paperName + '\'' +
                ", publicationName='" + publicationName + '\'' +
                ", paperStatus='" + paperStatus + '\'' +
                ", publicationTime='" + publicationTime + '\'' +
                ", indexType='" + indexType + '\'' +
                ", attributionLibrary='" + attributionLibrary + '\'' +
                ", pdf='" + pdf + '\'' +
                '}';
    }
}
