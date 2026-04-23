package ht.fds.mbds.michel.llm;

public record LlmInteraction(
        String questionJson,
        String reponseJson,
        String reponseExtraite
) {}
