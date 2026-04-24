package tech.aiflowy.common.constant.enums;

import tech.aiflowy.common.annotation.DictDef;

@DictDef(name = "wiki类型", code = "wikiType", keyField = "code", labelField = "text")
public enum EnumWikiType {

    // 1目录2正文
    DIRECTORY(1, "目录"),
    CONTENT(2, "正文")
    ;

    private Integer code;
    private String text;

    EnumWikiType(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static EnumWikiType getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (EnumWikiType type : EnumWikiType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }
        throw new RuntimeException("内容类型非法");
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }}
