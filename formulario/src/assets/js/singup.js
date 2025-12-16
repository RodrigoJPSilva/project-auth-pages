document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("signupForm"); 

    form.addEventListener("submit", async (event) => {
        event.preventDefault(); 

        const name = document.getElementById("name").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const user = { 
            name: name, // ou "nome": name
            email: email, 
            password: password 
        };

        try {
            // 3. Envia para a rota de CADASTRO
            const response = await fetch("http://localhost:8084/usuario/cadastro", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(user)
            });

            if (!response.ok) {
                // Lida com erros (ex: "Email já cadastrado", erro 400, etc)
                const errorText = await response.text();
                throw new Error(errorText || "Erro ao realizar cadastro");
            }

            // 4. Sucesso
            const data = await response.text();
            alert("Cadastro realizado com sucesso! Redirecionando para o login...");
            
            // Redireciona o usuário para fazer login
            window.location.href = "signin.html"; 

        } catch (error) {
            console.error("Erro detalhado:", error);
            alert(error.message === "Failed to fetch" 
                ? "Servidor offline ou erro de conexão." 
                : error.message);
        }
    });
});