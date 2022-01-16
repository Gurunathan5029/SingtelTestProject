package Constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class Enums {
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum ToDoType {
        ALL(""),
        ACTIVE(""),
        //Added space upfront to match the UI path
        COMPLETED(".completed");
        @Getter
        private String toDoTypeUIPathStr;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum InputTextType {
        ALPHA_NUMERIC("Wake up at 6:30 AM"),
        SPECIAL("Unlock the phone using password '!@#$%^\"\\\\\"\"&*<>,./:;()_'"),
        UNICODE("乘坐巴士@8:30"),
        BLANK("ㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤㅤ"),
        LENGTHY("This is a chance for you to display your test coding skills, code quality and hygiene.You should provide a clear README instruction on how to run your test.");
        @Getter
        private String textType;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public enum FlowType{
        ENTERED,
        EDITED,
        DELETED;
    }
}
