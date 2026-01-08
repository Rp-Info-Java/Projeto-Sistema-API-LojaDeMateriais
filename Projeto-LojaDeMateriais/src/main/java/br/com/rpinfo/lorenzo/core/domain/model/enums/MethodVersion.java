package br.com.rpinfo.lorenzo.core.domain.model.enums;

public enum MethodVersion {
    V_1_0;

    public static MethodVersion fromValue(String value) {
        value = value.replace("v", "V_");
        value = value.replace(".", "_");
        try {
            return MethodVersion.valueOf(value);
        } catch (Exception e) {
            return null;
        }
    }
}
