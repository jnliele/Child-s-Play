package com.example.teachingtoddlers;

public class alphabetQuestions {
    
    public String questions[] = {
            "What letter goes in the blank\n_ b c d e",
            "What letter goes in the blank\na _ c d e",
            "What letter goes in the blank\na b _ d e",
            "What letter goes in the blank\nb c _ e f",
            "What letter goes in the blank\nc d _ f g",
            "What letter goes in the blank\nd e _ g h",
            "What letter goes in the blank\ne f _ h i",
            "What letter goes in the blank\nf g _ i j",
            "What letter goes in the blank\ng h _ j k",
            "What letter goes in the blank\nh i _ k l",
            "What letter goes in the blank\ni j _ l m",
            "What letter goes in the blank\nj k _ m n",
            "What letter goes in the blank\nk l _ n o",
            "What letter goes in the blank\nl m _ o p",
            "What letter goes in the blank\nm n _ p q",
            "What letter goes in the blank\nn o _ q r",
            "What letter goes in the blank\no p _ r s",
            "What letter goes in the blank\np q _ s t",
            "What letter goes in the blank\nq r _ t u",
            "What letter goes in the blank\nr s _ u v",
            "What letter goes in the blank\ns t _ v w",
            "What letter goes in the blank\nt u _ w x",
            "What letter goes in the blank\nu v _ x y",
            "What letter goes in the blank\nv w _ y z",
            "What letter goes in the blank\nv w x _ z",
            "What letter goes in the blank\nv w x y _"
    };

    private String choices[][] = {
            {"f", "a", "g", "e"},
            {"e", "r", "w", "b"},
            {"c", "t", "s", "f"},
            {"h", "d", "g", "e"},
            {"s", "e", "v", "c"},
            {"k", "e", "d", "f"},
            {"h", "y", "g", "q"},
            {"s", "h", "r", "f"},
            {"y", "e", "i", "f"},
            {"j", "y", "g", "n"},
            {"n", "k", "f", "c"},
            {"i", "j", "s", "l"},
            {"i", "f", "e", "m"},
            {"v", "j", "n", "y"},
            {"o", "w", "h", "r"},
            {"j", "p", "s", "f"},
            {"s", "q", "w", "t"},
            {"r", "f", "n", "g"},
            {"a", "w", "s", "k"},
            {"j", "y", "u", "t"},
            {"t", "u", "e", "b"},
            {"i", "f", "v", "h"},
            {"w", "o", "g", "z"},
            {"u", "x", "k", "z"},
            {"e", "r", "c", "y"},
            {"f", "z", "k", "p"}
    };

    private String answers[] = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","x","y","z"};

    public String getQuestion(int a){
        String question = questions[a];
        return question;
    }

    public String getChoice1(int a){
        String choice = choices[a][0];
        return choice;
    }

    public String getChoice2(int a){
        String choice = choices[a][1];
        return choice;
    }

    public String getChoice3(int a){
        String choice = choices[a][2];
        return choice;
    }

    public String getChoice4(int a){
        String choice = choices[a][3];
        return choice;
    }

    public String correct(int a){
        String answer = answers[a];
        return answer;
    }
}
