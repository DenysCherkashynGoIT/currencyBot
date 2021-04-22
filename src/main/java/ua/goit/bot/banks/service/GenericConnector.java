package ua.goit.bot.banks.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.goit.bot.banks.service.BankApiConnector;
import ua.goit.bot.system.BankResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class GenericConnector<T> implements BankApiConnector {
    private static final Gson GSON = new Gson();
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    public TypeToken<T> typeToken;

    public GenericConnector(Class<T> tClass) {
        this.typeToken = TypeToken.get(tClass);
    }

    abstract String getUrl();

    abstract Function<T, BankResponse> mappingFunction();

    public List<BankResponse> getHttpResponse() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(getUrl()))
                .header("Content-type", "application/json").GET().build();

        try {
            HttpResponse<String> response = HTTP_CLIENT
                    .send(request, HttpResponse.BodyHandlers.ofString());
            List<T> list = GSON.fromJson(response.body(),
                    TypeToken.getParameterized(List.class, typeToken.getType())
                            .getType());
            return list.stream().map(mappingFunction())
                    .collect(Collectors.toList());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
