package dev.wiji.Zephyr.compatibility.Exceptions;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PacketParameterException extends RuntimeException {

    public PacketParameterException(List<Field> parameters) {
        super(getMessage(parameters));

    }

    private static String getMessage(List<Field> parameters) {
        StringBuilder builder = new StringBuilder("Invalid Parameters. Correct Params: ");

        for(int i = 0; i < parameters.size(); i++) {
            Field parameter = parameters.get(i);

            builder.append(parameter.getType().getName()).append(" ").append(parameter.getName());
            if(i < (parameters.size() - 1)) builder.append(", ");
        }

        return builder.toString();
    }
}
