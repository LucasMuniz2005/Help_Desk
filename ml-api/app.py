from flask import Flask, request, jsonify
import joblib
import os

app = Flask(__name__)

modelo = joblib.load("modelo_sentimento.pkl")
vetorizador = joblib.load("vetorizador.pkl")

@app.route("/sentimento", methods=["POST"])
def classificar_sentimento():
    dados = request.get_json()
    texto = dados.get("texto", "")

    if not texto:
        return jsonify({"erro": "Campo 'texto' é obrigatório"}), 400

    texto_vetorizado = vetorizador.transform([texto])
    sentimento = modelo.predict(texto_vetorizado)[0]

    return jsonify({"sentimento": sentimento})

if __name__ == "__main__":
    porta = int(os.environ.get("PORT", 5000))
    app.run(host="0.0.0.0", port=porta)