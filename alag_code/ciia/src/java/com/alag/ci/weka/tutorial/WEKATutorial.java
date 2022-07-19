package com.alag.ci.weka.tutorial;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

public class WEKATutorial {
    
    public static void main(String [] args) throws Exception {
        WEKATutorial wekaTut = new WEKATutorial();
        wekaTut.executeWekaTutorial();
    }
    
    private void executeWekaTutorial() throws Exception {
    	//		Create attributes
        FastVector allAttributes = createAttributes();   
        
        //		Create dataset for learning
        Instances learningDataset = createLearningDataSet(allAttributes);    
        
        //		Build predictive model
        Classifier predictiveModel = learnPredictiveModel( learningDataset);
        
        //		Evaluate predictive model
        Evaluation evaluation = evaluatePredictiveModel(predictiveModel,  learningDataset);
        System.out.println(evaluation.toSummaryString());
        predictUnknownCases(learningDataset,predictiveModel);	//	Predict unknown cases
   //     plotData(learningDataset, predictiveModel);
    }
    
    //		Predicting number of logins
    private void predictUnknownCases(Instances learningDataset, Classifier predictiveModel) 
        throws Exception {
        Instance testMaleInstance = 
            createInstance(learningDataset,31., "male", 0) ;
        Instance testFemaleInstance = 
            createInstance(learningDataset,31., "female", 0) ;
        double malePrediction = 
            predictiveModel.classifyInstance(testMaleInstance);
        double femalePrediction = 
            predictiveModel.classifyInstance(testFemaleInstance);
        System.out.println("Predicted number of logins [age=31]: ");
        System.out.println("\tMale = " + malePrediction);
        System.out.println("\tFemale = " + femalePrediction);
    }

    private FastVector createAttributes() {
        //create the numeric attribute Age
        Attribute ageAttribute = new Attribute("age");
        //create the nominal attribute Gender
        FastVector genderAttributeValues = new FastVector(2);
        genderAttributeValues.addElement("male");
        genderAttributeValues.addElement("female");
        Attribute genderAttribute = new Attribute("gender", genderAttributeValues);
        //create the numLogins attribute
        Attribute numLoginsAttribute = new Attribute("numLogins");
        FastVector allAttributes = new FastVector(3);
        allAttributes.addElement(ageAttribute);
        allAttributes.addElement(genderAttribute);
        allAttributes.addElement(numLoginsAttribute);
        return allAttributes;
    }
    
    //		Instance for dataset sample
    private Instances createLearningDataSet(FastVector allAttributes) {
        Instances trainingDataSet = 
            new Instances("wekaTutorial", allAttributes, 4); 
        trainingDataSet.setClassIndex(2);
        addInstance(trainingDataSet, 20.,"male", 5);
        addInstance(trainingDataSet, 30.,"female", 2);
        addInstance(trainingDataSet, 40.,"male", 3);
        addInstance(trainingDataSet, 35.,"female", 4);
        addInstance(trainingDataSet, 30.,"male", 2);
        addInstance(trainingDataSet, 25.,"female", 3);
//        addInstance(trainingDataSet, 35.,"female", 5);
        return trainingDataSet;
    }
    
    private void addInstance(Instances trainingDataSet, 
            double age, String gender, int numLogins)  {        
        Instance instance = createInstance(trainingDataSet,age, gender,numLogins);
        trainingDataSet.add(instance);
    }
    
    private Instance createInstance(Instances associatedDataSet,
            double age, String gender, int numLogins) {
        // Create empty instance with three attribute values
        Instance instance = new Instance(3);
        instance.setDataset(associatedDataSet);
        instance.setValue(0, age);
        instance.setValue(1, gender);
        instance.setValue(2, numLogins);
        return instance;
    }
    
    private Classifier learnPredictiveModel(Instances learningDataset) throws Exception {
       Classifier classifier = getClassifier();
       classifier.buildClassifier(learningDataset);
       return classifier;
    }
    
    private Classifier getClassifier() {
        RBFNetwork rbfLearner = new RBFNetwork();
        rbfLearner.setNumClusters(2);
        return rbfLearner;
    }
    
    private Evaluation evaluatePredictiveModel(Classifier classifier, Instances learningDataset) throws Exception {
        Evaluation learningSetEvaluation = new Evaluation(learningDataset);
        learningSetEvaluation.evaluateModel(classifier, learningDataset);
        return learningSetEvaluation;
    }

//    private void plotData(Instances learningDataset, Classifier predictiveModel) throws Exception {
//        for (int i = 20;  i <= 40; i ++) {
//            Instance testMaleInstance = createInstance(learningDataset,i, "male", 0) ;
//            Instance testFemaleInstance = createInstance(learningDataset,i, "female", 0) ;
//            System.out.println(i + ",male," + predictiveModel.classifyInstance(testMaleInstance));
//            System.out.println(i + ",female," + predictiveModel.classifyInstance(testFemaleInstance));
//        }
//    }
}
