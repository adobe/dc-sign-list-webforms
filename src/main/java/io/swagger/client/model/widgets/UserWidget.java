/************************************************************************
 *
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 * Copyright 2023 Adobe
 * All Rights Reserved.
 *
 * NOTICE: All information contained herein is, and remains
 * the property of Adobe and its suppliers, if any. The intellectual
 * and technical concepts contained herein are proprietary to Adobe
 * and its suppliers and are protected by all applicable intellectual
 * property laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe.

 *************************************************************************
 */

package io.swagger.client.model.widgets;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.Date;

/**
 * UserWidget
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-03-11T15:50:01.583+05:30")
public class UserWidget {
    @SerializedName("id")
    private String id = null;

    @SerializedName("groupId")
    private String groupId = null;

    @SerializedName("hidden")
    private Boolean hidden = null;

    @SerializedName("javascript")
    private String javascript = null;

    @SerializedName("modifiedDate")
    private Date modifiedDate = null;

    @SerializedName("name")
    private String name = null;

    /**
     * The widget status (AUTHORING, ACTIVE, DRAFT, DISABLED)
     */
    @JsonAdapter(StatusEnum.Adapter.class)
    public enum StatusEnum {
        AUTHORING("AUTHORING"),

        ACTIVE("ACTIVE"),

        DRAFT("DRAFT"),

        DISABLED("DISABLED");

        private String value;

        StatusEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static StatusEnum fromValue(String text) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }
            return null;
        }

        public static class Adapter extends TypeAdapter<StatusEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatusEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return StatusEnum.fromValue(String.valueOf(value));
            }
        }
    }

    @SerializedName("status")
    private StatusEnum status = null;

    @SerializedName("url")
    private String url = null;

    public UserWidget id(String id) {
        this.id = id;
        return this;
    }

    /**
     * The unique identifier of a widget
     * @return id
     **/
    @ApiModelProperty(value = "The unique identifier of a widget")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserWidget groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     * The unique identifier of a widget's group
     * @return groupId
     **/
    @ApiModelProperty(value = "The unique identifier of a widget's group")
    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public UserWidget hidden(Boolean hidden) {
        this.hidden = hidden;
        return this;
    }

    /**
     * True if agreement is hidden for the user
     * @return hidden
     **/
    @ApiModelProperty(value = "True if agreement is hidden for the user")
    public Boolean isHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public UserWidget javascript(String javascript) {
        this.javascript = javascript;
        return this;
    }

    /**
     * The embedded javascript code of the widget
     * @return javascript
     **/
    @ApiModelProperty(value = "The embedded javascript code of the widget")
    public String getJavascript() {
        return javascript;
    }

    public void setJavascript(String javascript) {
        this.javascript = javascript;
    }

    public UserWidget modifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    /**
     * The date on which the widget was last modified. Format would be yyyy-MM-dd&#39;T&#39;HH:mm:ssZ. For example, e.g 2016-02-25T18:46:19Z represents UTC time
     * @return modifiedDate
     **/
    @ApiModelProperty(value = "The date on which the widget was last modified. Format would be yyyy-MM-dd'T'HH:mm:ssZ. For example, e.g 2016-02-25T18:46:19Z represents UTC time")
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UserWidget name(String name) {
        this.name = name;
        return this;
    }

    /**
     * The name of the widget.
     * @return name
     **/
    @ApiModelProperty(value = "The name of the widget.")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserWidget status(StatusEnum status) {
        this.status = status;
        return this;
    }

    /**
     * The widget status (AUTHORING, ACTIVE, DRAFT, DISABLED)
     * @return status
     **/
    @ApiModelProperty(value = "The widget status (AUTHORING, ACTIVE, DRAFT, DISABLED)")
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public UserWidget url(String url) {
        this.url = url;
        return this;
    }

    /**
     * The hosted url of the widget
     * @return url
     **/
    @ApiModelProperty(value = "The hosted url of the widget")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserWidget userWidget = (UserWidget) o;
        return Objects.equals(this.id, userWidget.id) &&
                Objects.equals(this.hidden, userWidget.hidden) &&
                Objects.equals(this.javascript, userWidget.javascript) &&
                Objects.equals(this.modifiedDate, userWidget.modifiedDate) &&
                Objects.equals(this.name, userWidget.name) &&
                Objects.equals(this.status, userWidget.status) &&
                Objects.equals(this.url, userWidget.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, hidden, javascript, modifiedDate, name, status, url);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UserWidget {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    groupId: ").append(toIndentedString(groupId)).append("\n");
        sb.append("    hidden: ").append(toIndentedString(hidden)).append("\n");
        sb.append("    javascript: ").append(toIndentedString(javascript)).append("\n");
        sb.append("    modifiedDate: ").append(toIndentedString(modifiedDate)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    url: ").append(toIndentedString(url)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}