package com.mehdi.sprinairag;

import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;

import java.util.List;

public class Test1 {

    public static void main(String[] args) {
        OpenAiApi openAiApi =new OpenAiApi("sk-proj-lx2DBLW1L4UjZKT1KLUnT3BlbkFJqkOT6O5ZEN8XUqUXN3Nl");
        OpenAiChatModel openAiChatModel =new OpenAiChatModel(openAiApi ,
                OpenAiChatOptions.builder()
                        .withModel("gpt-4o")
                        .withTemperature(0F)
                        .withMaxTokens(300)
                        .build()

        );
        String  systemMessageText= """
        Vous étes un asistant spécialié dans le demaine de l'analyse des sentiment.
        Votre Taches est d'extraire a partir un commentaire le sentiment des differents aspects
        de ordinateurs achetés par des client . les aspects qui nous sont interessent sont :
        l'ecran , la souris et le clavier. le sentiment peut etre : positive , negative ou neutre 
        le resultat attendu sera au format JSON avec les champs suivants :
        - clavier : le sentiment relatif au clavier
        - souris : le sentiment ralatif a la souris
        -ecran : le sentiment relatif a l'ecran
        """;
        SystemMessage systemMessage=new SystemMessage(systemMessageText);
        String userInputeText1= """
                je suis satifait par la qualité de l'ecran , mais le clavier estmauvais alors que pour la souris la qualite est plott moyenne
                par aiileur le pense que cet ordinateur comme somme beaucoup d'énergie
                """;
        UserMessage userMessage=new UserMessage(userInputeText1);
        Prompt prompt =new Prompt(List.of(systemMessage,userMessage));
        ChatResponse chatResponse=openAiChatModel.call(prompt);
        System.out.println(chatResponse.getResult().getOutput().getContent());
    }

}
