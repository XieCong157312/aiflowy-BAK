package tech.aiflowy.common.constant.enums;

import tech.aiflowy.common.annotation.DictDef;

@DictDef(name = "识别模式", code = "recognitionMode", keyField = "code", labelField = "text")
public enum EnumRecognitionMode {

    NORMAL(1, "普通识别"),
    OCR(2, "OCR识别"),
    ;

    private Integer code;
    private String text;

    EnumRecognitionMode(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    public static EnumRecognitionMode getByCode(Integer code) {
        if (null == code) {
            return null;
        }
        for (EnumRecognitionMode type : EnumRecognitionMode.values()) {
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
