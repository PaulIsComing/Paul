package com.yuezhou.shrioboot.po.enums;

public enum PageEnum {
    HOME(100),
    MANAGEMENT(200);

    private int page;

    PageEnum(int page) {
        this.page = page;
    }

    public String getPageAction() {
        return String.valueOf(page);
    }

    public String getPageAction(ActionEnum action) {
        return String.valueOf(page + action.getAction());
    }

    public enum ActionEnum {

        CREAT(2),
        READ(1),
        UPDATE(3),
        DELETE(4);

        private int action;

        ActionEnum(int action) {
            this.action = action;
        }

        public int getAction() {
            return action;
        }
    }
}
