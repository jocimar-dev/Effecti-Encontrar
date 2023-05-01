document.getElementById("htmlForm").addEventListener("submit", function (event) {
    event.preventDefault();
    const htmlContent = document.getElementById("htmlContent").value;
    fetch("http://localhost:8080/effecti/", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(htmlContent)
    }).then(response => {
        if (response.status === 201) {
            getPregoes();
        }
    });
});

function getPregoes() {
    fetch("http://localhost:8080/effecti/listar.html")
        .then(response => response.json())
        .then(pregoes => {
            const pregoesTableBody = document.querySelector("#pregoesTable tbody");
            pregoesTableBody.innerHTML = "";
            pregoes.forEach(data => {
                const row = document.createElement("tr");
                row.innerHTML = `
                            <td>${data.uasg}</td>
                            <td>${data.pregaoEletronico}</td>
                            <td>${data.objeto}</td>
                            <td>${data.editalData}</td>
                            <td>${data.endereco}</td>
                            <td>${data.telefone}</td>
                            <td>${data.fax}</td>
                            <td>${data.entregaPropostaData}</td>
                        `;
                pregoesTableBody.appendChild(row);
            });
        });
}
getPregoes();