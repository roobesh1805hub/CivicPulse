import pandas as pd
import joblib
import os

from sklearn.model_selection import train_test_split
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.linear_model import LogisticRegression
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import LinearSVC
from sklearn.metrics import accuracy_score


# ==========================================
# 1. LOAD DATASET
# ==========================================

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

dataset_path = os.path.join(
    BASE_DIR,
    "dataset",
    "complaints.csv"
)

data = pd.read_csv(dataset_path)

print("==========================================")
print("SMART CITY AI - COMPLAINT PRIORITY MODEL")
print("==========================================")

print("\nDataset loaded successfully!")
print("Number of complaints:", len(data))

print("\nColumns:")
print(data.columns.tolist())


# ==========================================
# 2. SELECT INPUT AND OUTPUT
# ==========================================

X = data["complaint_text"]
y = data["priority"]

print("\nTarget variable: priority")
print("Priority classes:", y.unique())


# ==========================================
# 3. CONVERT TEXT INTO NUMBERS USING TF-IDF
# ==========================================

vectorizer = TfidfVectorizer(
    lowercase=True,
    stop_words="english"
)

X_tfidf = vectorizer.fit_transform(X)

print("\nTF-IDF conversion completed!")
print("Number of features:", X_tfidf.shape[1])


# ==========================================
# 4. TRAIN / TEST SPLIT
# ==========================================

X_train, X_test, y_train, y_test = train_test_split(
    X_tfidf,
    y,
    test_size=0.2,
    random_state=42,
    stratify=y
)

print("\nTraining complaints:", X_train.shape[0])
print("Testing complaints:", X_test.shape[0])


# ==========================================
# 5. LOGISTIC REGRESSION
# ==========================================

logistic_model = LogisticRegression(
    max_iter=1000
)

logistic_model.fit(X_train, y_train)

logistic_predictions = logistic_model.predict(X_test)

logistic_accuracy = accuracy_score(
    y_test,
    logistic_predictions
)


# ==========================================
# 6. RANDOM FOREST
# ==========================================

random_forest_model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

random_forest_model.fit(X_train, y_train)

random_forest_predictions = random_forest_model.predict(X_test)

random_forest_accuracy = accuracy_score(
    y_test,
    random_forest_predictions
)


# ==========================================
# 7. SUPPORT VECTOR MACHINE
# ==========================================

svm_model = LinearSVC(
    random_state=42
)

svm_model.fit(X_train, y_train)

svm_predictions = svm_model.predict(X_test)

svm_accuracy = accuracy_score(
    y_test,
    svm_predictions
)


# ==========================================
# 8. DISPLAY RESULTS
# ==========================================

print("\n==========================================")
print("MODEL ACCURACY COMPARISON")
print("==========================================")

print(
    f"Logistic Regression : {logistic_accuracy * 100:.2f}%"
)

print(
    f"Random Forest       : {random_forest_accuracy * 100:.2f}%"
)

print(
    f"SVM                 : {svm_accuracy * 100:.2f}%"
)


# ==========================================
# 9. SELECT BEST MODEL
# ==========================================

models = {
    "Logistic Regression": (
        logistic_accuracy,
        logistic_model
    ),
    "Random Forest": (
        random_forest_accuracy,
        random_forest_model
    ),
    "SVM": (
        svm_accuracy,
        svm_model
    )
}

best_model_name = max(
    models,
    key=lambda name: models[name][0]
)

best_accuracy = models[best_model_name][0]
best_model = models[best_model_name][1]


print("\n==========================================")
print("BEST MODEL")
print("==========================================")

print("Model:", best_model_name)
print(f"Accuracy: {best_accuracy * 100:.2f}%")


# ==========================================
# 10. TEST WITH SAMPLE COMPLAINTS
# ==========================================

sample_complaints = [
    "Ambulance is stuck in heavy traffic",
    "Street light is not working",
    "Water pipe is leaking badly",
    "Garbage has not been collected"
]

sample_tfidf = vectorizer.transform(sample_complaints)

sample_predictions = best_model.predict(sample_tfidf)

print("\n==========================================")
print("SAMPLE AI PREDICTIONS")
print("==========================================")

for complaint, prediction in zip(
        sample_complaints,
        sample_predictions
):
    print("\nComplaint:", complaint)
    print("Predicted Priority:", prediction)

# ==========================================
# 11. SAVE MODEL AND VECTORIZER
# ==========================================

model_directory = "../models"

os.makedirs(model_directory, exist_ok=True)

model_path = os.path.join(
    model_directory,
    "best_model.pkl"
)

vectorizer_path = os.path.join(
    model_directory,
    "tfidf_vectorizer.pkl"
)

joblib.dump(best_model, model_path)

joblib.dump(vectorizer, vectorizer_path)

print("\n==========================================")
print("MODEL SAVED SUCCESSFULLY")
print("==========================================")

print("Model:", model_path)
print("Vectorizer:", vectorizer_path)

print("\n==========================================")
print("MODEL TRAINING COMPLETED")
print("==========================================")