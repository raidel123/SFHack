package com.example.admin.sfhack;

import java.io.Serializable;

/**
 * Created by admin on 6/15/17.
 */

public class DocumentInfo implements Serializable {

    private String docTitle;
    private String docUrl;

    public DocumentInfo(){
        this.docTitle = "blank";
        this.docUrl = "blank";
    }

    public DocumentInfo(String docTitle, String docUrl) {
        this.docTitle = docTitle;
        this.docUrl = docUrl;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocUrl() {
        return docUrl;
    }

    public void setDocUrl(String docUrl) {
        this.docUrl = docUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentInfo that = (DocumentInfo) o;

        return docUrl.equals(that.docUrl);

    }

    @Override
    public int hashCode() {
        int result = docTitle.hashCode();
        result = 31 * result + docUrl.hashCode();
        return result;
    }
}
