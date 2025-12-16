document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm"); 
    form.addEventListener("submit", async (event) => {
        event.preventDefault();

        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;

        const user = { email, password };

        try {
            const response = await fetch("http://localhost:8084/usuario/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            });

            if (!response.ok) {
                // Lida com erros HTTP (401, 404, 500, etc)
                const errorText = await response.text();
                throw new Error(errorText || "Erro na autenticação");
            }

            const data = await response.text();
            alert("Sucesso: " + data);
            
            // Exemplo: Redirecionar após login
            // window.location.href = "dashboard.html";

        } catch (error) {
            console.error("Erro detalhado:", error);
            alert(error.message === "Failed to fetch" 
                ? "Servidor offline ou erro de conexão." 
                : error.message);
        }
    });
});