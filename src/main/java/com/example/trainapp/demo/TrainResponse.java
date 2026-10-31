
package com.example.trainapp.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record TrainResponse(

                @JsonProperty("DESTINATION") String destination,

                @JsonProperty("DIRECTION") String direction,

                @JsonProperty("EVENT_TIME") String eventTime,

                @JsonProperty("IS_REALTIME") String isRealtime,

                @JsonProperty("LINE") String line,

                @JsonProperty("NEXT_ARR") String nextArrival,

                @JsonProperty("STATION") String station,

                @JsonProperty("TRAIN_ID") String trainId,

                @JsonProperty("WAITING_SECONDS") Integer waitingSeconds,

                @JsonProperty("WAITING_TIME") String waitingTime

) {
}
