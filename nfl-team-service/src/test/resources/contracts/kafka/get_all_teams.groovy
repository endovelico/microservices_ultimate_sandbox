package contracts.kafka

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should publish teams retrieval event"

    label "teams_retrieval"

    input {
        triggeredBy("triggerGetAllTeams()")
    }

    outputMessage {
        sentTo("teams-topic")

        body([
                message: $(regex(".*Team A.*"))
        ])

        headers {
            header("contentType", "application/json")
        }
    }
}
