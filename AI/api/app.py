from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import os


# ==========================================
# CREATE FASTAPI APPLICATION
# ==========================================

app = FastAPI(
    title="Smart City AI Complaint API",
    description="AI-based complaint priority prediction",
    version="1.0"
)


# ==========================================
# LOAD TRAINED MODEL
# ==========================================

BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))

MODEL_PATH = os.path.join(
    BASE_DIR,
    "models",
    "best_model.pkl"
)

VECTORIZER_PATH = os.path.join(
    BASE_DIR,
    "models",
    "tfidf_vectorizer.pkl"
)

model = joblib.load(MODEL_PATH)

vectorizer = joblib.load(VECTORIZER_PATH)


# ==========================================
# REQUEST DATA STRUCTURE
# ==========================================

class ComplaintRequest(BaseModel):
    complaint_text: str


# ==========================================
# HOME ENDPOINT
# ==========================================

@app.get("/")
def home():
    return {
        "message": "Smart City AI API is running"
    }


# ==========================================
# AI PREDICTION ENDPOINT
# ==========================================

@app.post("/predict")
def predict_priority(request: ComplaintRequest):

    complaint_text = request.complaint_text

    # Convert complaint text into TF-IDF
    complaint_tfidf = vectorizer.transform(
        [complaint_text]
    )

    # Predict priority using trained ML model
    prediction = model.predict(
        complaint_tfidf
    )[0]

    return {
        "complaint": complaint_text,
        "predicted_priority": prediction
    }
