package net.genemis.dispatcher.webapi;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import net.genemis.dispatcher.timeseries.DataPoint;
import net.genemis.dispatcher.timeseries.TimeSeriesRouter;
import net.genemis.dispatcher.timeseries.TimeSeriesService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.TreeSet;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TimeSeriesRouter.class, TimeSeriesService.class})
public class TimeSeriesApiTests extends AbstractWebapiTestHelper {

    @MockBean
    private TimeSeriesService timeSeriesService;
    private Set<DataPoint> timeSeries;

    @BeforeAll
    public void setUp() {
        super.setUp();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        timeSeries = new TreeSet<>();
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("UTC"));
        timeSeries.add(new DataPoint(now, 1.0, true));
        timeSeries.add(new DataPoint(now.plusDays(1), 2.0, true));
    }

    @Test
    public void testGetTimeSeries() throws Exception {
        Mockito.when(timeSeriesService.getTimeSeries()).thenReturn(timeSeries);

        EntityExchangeResult<byte[]> result = webClient.get().uri("/timeseries").exchange()
                .expectStatus().isOk()
                .expectBody()
                .returnResult();

        ObjectReader or = objectMapper.reader();
        JsonParser jsonParser = new JsonFactory().createParser(result.getResponseBody());
        TreeSet<DataPoint> data = or.readValue(jsonParser, new TypeReference<>(){});

        assertEquals(timeSeries, data);
    }

}
