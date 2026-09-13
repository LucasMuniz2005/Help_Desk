from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
import joblib

# Dataset sintético: textos de exemplo + o sentimento correspondente
textos = [
    "a impressora não funciona e já é a terceira vez, muito frustrante",
    "péssimo atendimento, ninguém resolve meu problema",
    "estou muito insatisfeito, o sistema trava toda hora",
    "isso é um absurdo, já pedi ajuda três vezes e nada",
    "horrível, perdi meu trabalho por causa desse erro",
    "muito ruim, o computador não liga de jeito nenhum",

    "preciso configurar o acesso ao sistema financeiro",
    "gostaria de solicitar a instalação de um novo programa",
    "o computador da sala 3 precisa de manutenção",
    "por favor, verifiquem a conexão de rede do setor",
    "solicito a troca do cabo de rede da minha estação",
    "preciso de acesso a uma nova impressora",

    "muito obrigado pelo atendimento rápido de ontem",
    "o problema foi resolvido rapidamente, excelente suporte",
    "ótimo trabalho da equipe de suporte técnico",
    "ficou tudo certo, agradeço a atenção",
    "que atendimento rápido, parabéns pela equipe",
    "sistema funcionando perfeitamente após a atualização",
]

sentimentos = [
    "NEGATIVO", "NEGATIVO", "NEGATIVO", "NEGATIVO", "NEGATIVO", "NEGATIVO",
    "NEUTRO", "NEUTRO", "NEUTRO", "NEUTRO", "NEUTRO", "NEUTRO",
    "POSITIVO", "POSITIVO", "POSITIVO", "POSITIVO", "POSITIVO", "POSITIVO",
]

# Transforma texto em números (vetorização TF-IDF)
vetorizador = TfidfVectorizer()
X = vetorizador.fit_transform(textos)

# Treina o modelo Naive Bayes
modelo = MultinomialNB()
modelo.fit(X, sentimentos)

# Salva o modelo e o vetorizador treinados em disco, pra reutilizar sem retreinar
joblib.dump(modelo, "modelo_sentimento.pkl")
joblib.dump(vetorizador, "vetorizador.pkl")

print("Modelo treinado e salvo com sucesso!")