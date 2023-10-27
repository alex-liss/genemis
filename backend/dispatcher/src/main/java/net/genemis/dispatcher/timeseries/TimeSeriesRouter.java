package net.genemis.dispatcher.timeseries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.TreeSet;

@Configuration
public class TimeSeriesRouter {

    private TimeSeriesService timeSeriesService;

    @Autowired
    public void setTimeSeriesService(TimeSeriesService timeSeriesService) {
        this.timeSeriesService = timeSeriesService;
    }

    @Bean
    public RouterFunction<ServerResponse> routeTimeSeries() {
        return RouterFunctions.route()
                .GET("/timeseriesdata", this::handleGetTimeSeries)
                .build();
    }

    private Mono<ServerResponse> handleGetTimeSeries(ServerRequest req) {
        TreeSet<DataPoint> timeSeries = timeSeriesService.getTimeSeries();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(timeSeries));
    }

}
