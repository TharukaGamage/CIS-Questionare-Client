package managers;


import models.Questionare;

//this singleton uses to keep the user answers start to end

public class QuestionareDataManager {

    private Questionare questionare;

    private static QuestionareDataManager instance = new QuestionareDataManager();

    private QuestionareDataManager() {
        questionare = new Questionare();
    }

    public static QuestionareDataManager getInstance() {
        return instance;
    }

    public Questionare getCurrentQuestionare(){
        return questionare;
    }
}
