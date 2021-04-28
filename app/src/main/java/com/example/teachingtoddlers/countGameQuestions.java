package com.example.teachingtoddlers;

public class countGameQuestions {

    public String Level1questions[] = {
            "What number goes in the blank\n_ 2 3 4 5",
            "What number goes in the blank\n1 _ 3 4 5",
            "What number goes in the blank\n1 2 _ 4 5",
            "What number goes in the blank\n1 2 3 _ 5",
            "What number goes in the blank\n1 2 3 4 _",
            "what number goes first?",
            "what number goes after 1?",
            "what number goes after 2?",
            "what number goes after 3?",
            "what number goes after 4?",
    };

    public String Level2questions[] = {
            "What number goes in the blank\n_ 2 3 4 5",
            "What number goes in the blank\n1 _ 3 4 5",
            "What number goes in the blank\n1 2 _ 4 5",
            "What number goes in the blank\n2 3 _ 5 6",
            "What number goes in the blank\n3 4 _ 6 7",
            "What number goes in the blank\n4 5 _ 7 8",
            "What number goes in the blank\n5 6 _ 8 9",
            "What number goes in the blank\n6 7 _ 9 10",
            "What number goes in the blank\n6 7 8 _ 10",
            "What number goes in the blank\n6 7 8 9 _",
    };

    public String Level3questions[] = {
            "What number goes in the blank\n_ 2 3 4 5",
            "What number goes in the blank\n1 _ 3 4 5",
            "What number goes in the blank\n1 2 _ 4 5",
            "What number goes in the blank\n2 3 _ 5 6",
            "What number goes in the blank\n3 4 _ 6 7",
            "What number goes in the blank\n4 5 _ 7 8",
            "What number goes in the blank\n5 6 _ 8 9",
            "What number goes in the blank\n6 7 _ 9 10",
            "What number goes in the blank\n7 8 _ 10 11",
            "What number goes in the blank\n8 9 _ 11 12",
            "What number goes in the blank\n9 10 _ 12 13",
            "What number goes in the blank\n10 11 _ 13 14",
            "What number goes in the blank\n11 12 _ 14 15",
            "What number goes in the blank\n12 13 _ 15 16",
            "What number goes in the blank\n13 14 _ 16 17",
            "What number goes in the blank\n14 15 _ 17 18",
            "What number goes in the blank\n15 16 _ 18 19",
            "What number goes in the blank\n16 17 _ 19 20",
            "What number goes in the blank\n16 17 18 _ 20",
            "What number goes in the blank\n16 17 18 19 _",
    };

    private String Level1choices[][] = {
            {"1", "4", "5", "2"},
            {"5", "1", "2", "3"},
            {"1", "3", "4", "2"},
            {"1", "5", "4", "3"},
            {"3", "2", "4", "5"},
            {"1", "4", "5", "2"},
            {"5", "1", "2", "3"},
            {"1", "3", "4", "2"},
            {"1", "5", "4", "3"},
            {"3", "2", "4", "5"}
    };

    private String Level2choices[][] = {
            {"1", "4", "5", "2"},
            {"5", "1", "2", "3"},
            {"1", "3", "4", "2"},
            {"1", "5", "4", "3"},
            {"3", "2", "4", "5"},
            {"2", "4", "6", "10"},
            {"9", "7", "4", "3"},
            {"4", "9", "10", "8"},
            {"9", "7", "6", "1"},
            {"7", "2", "10", "5"}
    };

    private String Level3choices[][] = {
            {"1", "4", "5", "2"},
            {"5", "1", "2", "3"},
            {"1", "3", "4", "2"},
            {"1", "5", "4", "3"},
            {"3", "2", "4", "5"},
            {"2", "4", "6", "10"},
            {"9", "7", "4", "3"},
            {"4", "9", "10", "8"},
            {"9", "7", "6", "1"},
            {"7", "2", "10", "5"},
            {"11", "2", "7", "9"},
            {"8", "12", "6", "3"},
            {"15", "18", "13", "20"},
            {"15", "13", "8", "14"},
            {"16", "17", "19", "15"},
            {"11", "19", "16", "20"},
            {"15", "17", "13", "7"},
            {"18", "16", "19", "8"},
            {"19", "1", "4", "9"},
            {"15", "20", "10", "5"}
    };

    private String Level1answers[] = {"1","2","3","4","5","1","2","3","4","5"};
    private String Level2answers[] = {"1","2","3","4","5","6","7","8","9","10"};
    private String Level3answers[] = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};

    //Level 1
    public String Level1getQuestion(int a){
        String question = Level1questions[a];
        return question;
    }

    public String Level1getChoice1(int a){
        String choice = Level1choices[a][0];
        return choice;
    }

    public String Level1getChoice2(int a){
        String choice = Level1choices[a][1];
        return choice;
    }

    public String Level1getChoice3(int a){
        String choice = Level1choices[a][2];
        return choice;
    }

    public String Level1getChoice4(int a){
        String choice = Level1choices[a][3];
        return choice;
    }

    public String Level1correct(int a){
        String answer = Level1answers[a];
        return answer;
    }

    //Level 2
    public String Level2getQuestion(int a){
        String question = Level2questions[a];
        return question;
    }

    public String Level2getChoice1(int a){
        String choice = Level2choices[a][0];
        return choice;
    }

    public String Level2getChoice2(int a){
        String choice = Level2choices[a][1];
        return choice;
    }

    public String Level2getChoice3(int a){
        String choice = Level2choices[a][2];
        return choice;
    }

    public String Level2getChoice4(int a){
        String choice = Level2choices[a][3];
        return choice;
    }

    public String Level2correct(int a){
        String answer = Level2answers[a];
        return answer;
    }

    // Level 3
    public String Level3getQuestion(int a){
        String question = Level3questions[a];
        return question;
    }

    public String Level3getChoice1(int a){
        String choice = Level3choices[a][0];
        return choice;
    }

    public String Level3getChoice2(int a){
        String choice = Level3choices[a][1];
        return choice;
    }

    public String Level3getChoice3(int a){
        String choice = Level3choices[a][2];
        return choice;
    }

    public String Level3getChoice4(int a){
        String choice = Level3choices[a][3];
        return choice;
    }

    public String Level3correct(int a){
        String answer = Level3answers[a];
        return answer;
    }

}

