package com.augmentum.entity;

public class WebContent {

    private String updateIfExists;
    private String groupName;
    private String name;
    private String type;
    private String description;
    private String categories;
    private String html;
    private String structureId;
    private String templateId;
    private String articleId;
    private String displayDate;

    public WebContent() {}
    
    public WebContent(String updateIfExists, String groupName, String name,
            String type, String description, String categories, String html,
            String structureId, String templateId, String articleId,
            String displayDate) {
        super();
        this.updateIfExists = updateIfExists;
        this.groupName = groupName;
        this.name = name;
        this.type = type;
        this.description = description;
        this.categories = categories;
        this.html = html;
        this.structureId = structureId;
        this.templateId = templateId;
        this.articleId = articleId;
        this.displayDate = displayDate;
    }
    
    public String getUpdateIfExists() {
        return updateIfExists;
    }

    public void setUpdateIfExists(String updateIfExists) {
        this.updateIfExists = updateIfExists;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getStructureId() {
        return structureId;
    }

    public void setStructureId(String structureId) {
        this.structureId = structureId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

}
