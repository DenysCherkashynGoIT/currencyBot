package ua.goit.bot.view;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.function.Consumer;

public class Check {
    private static final String MARKER = EmojiParser.parseToUnicode(":white_check_mark:");

    public static void many(InlineKeyboardMarkup markup, String... commands) {
        Consumer<InlineKeyboardButton> setMark = button -> {
            String buttonCommand = button.getCallbackData();
            Arrays.stream(commands).filter(command -> command.equals(buttonCommand))
                    .forEach(command -> {
                                String text = button.getText();
                                if (!isSet(text)) text = setMark(text);
                                button.setText(text);
                            }
                    );
        };
        checker(markup, setMark);
    }

    public static void one(InlineKeyboardMarkup markup, String command) {
        Consumer<InlineKeyboardButton> setMark = button -> {
            String buttonCommand = button.getCallbackData();
            if (command.equals(buttonCommand)) {
                String text = button.getText();
                if (!isSet(text)) text = setMark(text);
                button.setText(text);
            }
        };
        checker(markup, setMark);
    }

    private static void checker(InlineKeyboardMarkup markup, Consumer<InlineKeyboardButton> setMark) {
        markup.getKeyboard().forEach(row -> row.forEach(setMark));
    }

    private static boolean isSet(String text) {
        return text.contains(MARKER);
    }

    private static String setMark(String text) {
        return (isSet(text)) ? text : MARKER + "\t" + text;
    }
}