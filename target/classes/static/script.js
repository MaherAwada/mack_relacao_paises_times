function register() {
    fetch('http://localhost:8080/api/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: document.getElementById("register-email").value, // Seu email de registro
            password: document.getElementById("register-password").value,        // Sua senha de registro
        }),
    })
        .then(response => {
            if (response.status === 200) {
                alert('Sucesso no registro de usuario, codigo = ' + response.status);
                return response.text();
            } else {
                console.error("Falha no registro");
                alert('Falha no registro de usuário, erro codigo = ' + response.status);
            }
        })
        .then(message => {
            if (message) {
                console.log("Mensagem de confirmação: registro cadastrado com sucesso!");
                document.getElementById("register-result").textContent = "Mensagem de confirmação: registro cadastrado com sucesso!";
            }
        });
}

function authenticate() {
    fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            email: document.getElementById("email").value, // Seu email de registro
            password: document.getElementById("password").value,        // Sua senha de registro
        }),
    })
        .then(response => {
            if (response.status === 200) {
                console.log('Sucesso no login');
                alert('Sucesso no login, codigo = ' + response.status);
                return response.text();
            } else {
                alert('Falha no login, erro codigo = ' + response.status);
                document.getElementById("status").className = "status-dot";
                document.getElementById("login-result").textContent = "";
                document.getElementById("token").value = '';
                const countryNodeList = document.getElementById("country-list");
                countryNodeList.innerHTML = '';
                const teamNodeList = document.getElementById("team-list");
                teamNodeList.innerHTML = '';
            }
        })
        .then(message => {
            if (message) {
                const obj = JSON.parse(message);
                console.log("Mensagem de confirmação: login feito com sucesso! Agora você pode pesquisar pelos países!");
                document.getElementById("login-result").textContent = "Mensagem de confirmação: login feito com sucesso! Agora você pode pesquisar pelos países!";
                document.getElementById("status").className = "status-dot-logged";
                console.log('token = ' + obj.access_token);
                document.getElementById("token").value = obj.access_token;
                console.log('valor do hidden: ' + document.getElementById("token").value)
            }
        });
}

function countryRegister() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    fetch('http://localhost:8080/api/pais', {
        method: 'POST',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            nome: document.getElementById("pais-nome").value,
            continente: document.getElementById("pais-continente").value,
            populacao: document.getElementById("pais-populacao").value,
        }),
    })
        .then(response => {
            if (response.status === 201) {
                console.log('Sucesso no cadastro de pais!');
                console.log('response data from cad: ' + response.data);
                alert('Sucesso no cadastro de pais, codigo = ' + response.status);
                listCountries();
                return response.text();
            } else {
                console.error("Falha no registro de pais");
                alert("Falha no registro de pais, erro codigo = " + response.status);
            }
        })
        .then(message => {
            if (message) {
                console.log("Mensagem de confirmação: registro cadastrado com sucesso!");
                document.getElementById("register-result").textContent = "Mensagem de confirmação: registro cadastrado com sucesso!";
            }
        });
}

function teamRegister() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    fetch('http://localhost:8080/api/time', {
        method: 'POST',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            nome: document.getElementById("team-nome").value,
            anoDeFundacao: document.getElementById("team-anoFundacao").value,
            cidade: document.getElementById("team-cidade").value,
            estado: document.getElementById("team-estado").value,
        }),
    })
        .then(response => {
            if (response.status === 201) {
                console.log('Sucesso no cadastro de time!');
                alert("Sucesso no cadastro de time, codigo = " + response.status);
                listTeams()
                return response.text();
            } else {
                console.error("Falha no registro de team");
                alert("Falha no registro de team, erro codigo = " + response.status);
            }
        })
        .then(message => {
            if (message) {
                console.log("Mensagem de confirmação: registro cadastrado com sucesso!");
                document.getElementById("register-result").textContent = "Mensagem de confirmação: registro cadastrado com sucesso!";
            }
        });
}
function faqRegister() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    fetch('http://localhost:8080/api/faq', {
        method: 'POST',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            pergunta: document.getElementById("faq-pergunta").value,
            resposta: document.getElementById("faq-resposta").value
        }),
    })
        .then(response => {
            if (response.status === 201) {
                console.log('Sucesso no cadastro de FAQ!');
                alert("Sucesso no cadastro de FAQ, codigo = " + response.status);
                listFaq();
                return response.text();
            } else {
                alert('Falha no registro de FAQ, erro codigo = ' + response.status);
                console.error("Falha no registro de FAQ, erro codigo = " + response.status);
            }
        })
        .then(message => {
            if (message) {
                console.log("Mensagem de confirmação: registro cadastrado com sucesso!");
                document.getElementById("register-result").textContent = "Mensagem de confirmação: registro cadastrado com sucesso!";
            }
        });
}

function countryDeletion() {
    var id = this.event.target.id.split('_')[1];

    const bearerToken = `Bearer ${document.getElementById("token").value}`;
        console.log('bearerToken = ' + bearerToken)
        fetch('http://localhost:8080/api/pais/' + id, {
            method: 'DELETE',
            headers: {
                "Authorization": bearerToken,
                'Content-Type': 'application/json',
            }
        })
            .then(response => {
                if (response.status === 204) {
                    console.log('Sucesso ao deletar time!');
                    alert('Sucesso ao deletar, codigo = ' + response.status)
                    var parentLiId = 'li_' + id;
                    const parentNode = document.getElementById(parentLiId);
                    while (parentNode.firstChild) {
                      parentNode.removeChild(parentNode.firstChild);
                    }
                    document.getElementById(parentLiId).remove();
                } else {
                    console.error("Falha ao deletar pais");
                    alert('Falha ao deletar pais');
                }
            });
}

function listCountries() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    console.log('bearerToken = ' + bearerToken)
    fetch('http://localhost:8080/api/pais', {

        method: 'GET',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (response.status === 200) {
                console.log('Sucesso na listagem países');
                alert('Sucesso na listagem países, codigo = ' + response.status);
                return response.text();
            } else {
                console.error("Falha na busca por países");
                alert('Falha na busca por países, erro codigo = ' + response.status);
                document.getElementById("status").className = "status-dot";
                document.getElementById("login-result").textContent = "";
            }
        })
        .then(message => {
                    if (message) {
                        const teamNodeList = document.getElementById("country-list");
                        teamNodeList.innerHTML = '';
                        const listCountries = JSON.parse(message);

                        var ul = document.getElementById("country-list");

                        for (var i = 0; i < listCountries.length; i++) {
                            var country = listCountries[i];
                            console.log('listCountries ---> ' + JSON.stringify(country))
                            var li = document.createElement('li');
                            li.id = 'li_' + country.id;
                            li.appendChild(document.createTextNode(country.nome + " - " + country.continente + " - " + country.populacao));
                            var button = document.createElement('button');
                            button.id = 'countryDeletion_' + country.id;
                            button.innerHTML = 'Delete';
                            button.onclick = function() { countryDeletion };
                            button.style.backgroundColor = 'red';
                            li.appendChild(button);
                            ul.appendChild(li);
                        }
                    }
                });
}

function teamDeletion() {
    var id = this.event.target.id.split('_')[1];

    // back-end

    const bearerToken = `Bearer ${document.getElementById("token").value}`;
        console.log('bearerToken = ' + bearerToken)
        fetch('http://localhost:8080/api/time/' + id, {
            method: 'DELETE',
            headers: {
                "Authorization": bearerToken,
                'Content-Type': 'application/json',
            }
        })
            .then(response => {
                if (response.status === 204) {
                    console.log('Sucesso ao deletar time!');
                    alert('Sucesso ao deletar time, codigo = ' + response.status);
                    var parentLiId = 'li_' + id;
                    const parentNode = document.getElementById(parentLiId);
                    while (parentNode.firstChild) {
                      parentNode.removeChild(parentNode.firstChild);
                    }
                    document.getElementById(parentLiId).remove();
                } else {
                    console.error("Falha ao deletar time");
                    alert('Falha ao deletar time, erro codigo = ' + response.status);
                }
            });
}

function faqDeletion() {
    var id = this.event.target.id.split('_')[1];

    // back-end

    const bearerToken = `Bearer ${document.getElementById("token").value}`;
        console.log('bearerToken = ' + bearerToken)
        fetch('http://localhost:8080/api/faq/' + id, {
            method: 'DELETE',
            headers: {
                "Authorization": bearerToken,
                'Content-Type': 'application/json',
            }
        })
            .then(response => {
                if (response.status === 204) {
                    console.log('Sucesso ao deletar FAQ!');
                    alert('Sucesso ao deletar FAQ, codigo = ' + response.status);
                    var parentLiId = 'li_' + id;
                    const parentNode = document.getElementById(parentLiId);
                    while (parentNode.firstChild) {
                      parentNode.removeChild(parentNode.firstChild);
                    }
                    document.getElementById(parentLiId).remove();
                } else {
                    console.error("Falha ao deletar fAQ");
                    alert('Falha ao deletar FAQ, erro codigo = ' + response.status);
                }
            });
}

function listTeams() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    console.log('bearerToken = ' + bearerToken)
    fetch('http://localhost:8080/api/time', {

        method: 'GET',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (response.status === 200) {
                console.log('Sucesso na listagem times');
                alert('Sucesso na listagem times, codigo = ' + response.status);
                return response.text();
            } else {
                console.error("Falha na busca por times");
                alert('Falha na busca por times, erro codigo = ' + response.status);
                document.getElementById("status").className = "status-dot";
                document.getElementById("login-result").textContent = "";
            }
        })
        .then(message => {
            if (message) {
                const teamNodeList = document.getElementById("team-list");
                teamNodeList.innerHTML = '';
                const listTeams = JSON.parse(message);

                var ul = document.getElementById("team-list");

                for (var i = 0; i < listTeams.length; i++) {
                    var time = listTeams[i];
                    console.log('listTeams ---> ' + JSON.stringify(time))
                    var li = document.createElement('li');
                    li.id = 'li_' + time.id;
                    li.appendChild(document.createTextNode(time.nome + " - " + time.anoDeFundacao + " - " + time.cidade + " - " + time.estado));
                    var button = document.createElement('button');
                    button.id = 'teamDeletion_' + time.id;
                    button.innerHTML = 'Delete';
                    button.onclick = function() { teamDeletion };
                    button.style.backgroundColor = 'red';
                    li.appendChild(button);
                    ul.appendChild(li);
                }
            }
        });
}

function listFaq() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    console.log('bearerToken = ' + bearerToken)
    fetch('http://localhost:8080/api/faq', {

        method: 'GET',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (response.status === 200) {
                console.log('Sucesso na listagem FAQ');
                alert('Sucesso na listagem FAQ, codigo = ' + response.status);
                return response.text();
            } else {
                console.error("Falha na busca por FAQ");
                alert('Falha na busca por FAQ, erro codigo = ' + response.status);
                document.getElementById("status").className = "status-dot";
                document.getElementById("login-result").textContent = "";
            }
        })
        .then(message => {
            if (message) {
                const faqNodeList = document.getElementById("faq-list");
                faqNodeList.innerHTML = '';
                const listFaq = JSON.parse(message);

                var ul = document.getElementById("faq-list");

                for (var i = 0; i < listFaq.length; i++) {
                    var faq = listFaq[i];
                    console.log('listFaq ---> ' + JSON.stringify(faq))
                    var li = document.createElement('li');
                    li.id = 'li_' + faq.id;
                    li.appendChild(document.createTextNode("Pergunta: " + faq.pergunta + " "));
                    var button = document.createElement('button');
                    button.id = 'faqDeletion_' + faq.id;
                    button.innerHTML = 'Delete';
                    button.onclick = function() { faqDeletion };
                    button.style.backgroundColor = 'red';
                    li.appendChild(button);

                    var verRespostaButton = document.createElement('button');
                    verRespostaButton.id = 'faqResposta_' + faq.id;
                    verRespostaButton.innerHTML = 'Ver resposta';
                    verRespostaButton.onclick = function() { faqResposta };
                    verRespostaButton.style.backgroundColor = 'green';
                    li.appendChild(verRespostaButton);

                    ul.appendChild(li);
                }
            }
        });
}

function faqSearch() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    console.log('bearerToken = ' + bearerToken);
    const perguntaParam = document.getElementById("faq-pergunta-search").value;
    fetch('http://localhost:8080/api/faq?pergunta=' + perguntaParam, {
        method: 'GET',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (response.status === 200) {
                console.log('Sucesso na listagem com busca FAQ');
                alert('Sucesso na listagem com busca FAQ, codigo = ' + response.status);
                return response.text();
            } else {
                console.error("Falha na busca com filtro por FAQ");
                alert('Falha na busca com filtro por FAQ, erro codigo = ' + response.status);
                document.getElementById("status").className = "status-dot";
                document.getElementById("login-result").textContent = "";
            }
        })
        .then(message => {
            if (message) {
                const faqNodeList = document.getElementById("faq-list");
                faqNodeList.innerHTML = '';
                const listFaq = JSON.parse(message);

                var ul = document.getElementById("faq-list");

                for (var i = 0; i < listFaq.length; i++) {
                    var faq = listFaq[i];
                    console.log('listFaq ---> ' + JSON.stringify(faq))
                    var li = document.createElement('li');
                    li.id = 'li_' + faq.id;
                    li.appendChild(document.createTextNode("Pergunta: " + faq.pergunta + " "));
                    var button = document.createElement('button');
                    button.id = 'faqDeletion_' + faq.id;
                    button.innerHTML = 'Delete';
                    button.onclick = function() { faqDeletion };
                    button.style.backgroundColor = 'red';
                    li.appendChild(button);

                    var verRespostaButton = document.createElement('button');
                    verRespostaButton.id = 'faqResposta_' + faq.id;
                    verRespostaButton.innerHTML = 'Ver resposta';
                    verRespostaButton.onclick = function() { faqResposta };
                    verRespostaButton.style.backgroundColor = 'green';
                    li.appendChild(verRespostaButton);

                    ul.appendChild(li);
                }
            }
        });
}

function faqResposta() {
    const bearerToken = `Bearer ${document.getElementById("token").value}`;
    console.log('bearerToken = ' + bearerToken);
    var id = this.event.target.id.split('_')[1];
    fetch('http://localhost:8080/api/faq/' + id, {
        method: 'GET',
        headers: {
            "Authorization": bearerToken,
            'Content-Type': 'application/json',
        }
    })
        .then(response => {
            if (response.status === 200) {
                console.log('Sucesso ao ver resposta FAQ');
                alert('Sucesso ao ver resposta FAQ, codigo = ' + response.status);
                return response.text();
            } else {
                console.error("Falha na busca da resposta do FAQ");
                alert('Falha na busca da resposta do FAQ, erro codigo = ' + response.status);
                document.getElementById("status").className = "status-dot";
                document.getElementById("login-result").textContent = "";
            }
        })
        .then(message => {
            if (message) {
                const faqResult = JSON.parse(message);
                alert(faqResult.resposta);
            }
        });
}

document.body.addEventListener( 'click', function ( event ) {
    console.log('event.target.id --> ' + event.target.id)
    const id = event.target.id;
    if( id.startsWith('teamDeletion') ) {
        object_id = id.split('_')[1];
        teamDeletion(object_id);
    };
    if( id.startsWith('countryDeletion') ) {
        object_id = id.split('_')[1];
        countryDeletion(object_id);
    };

    if( id.startsWith('faqDeletion') ) {
        object_id = id.split('_')[1];
        faqDeletion(object_id);
    };

    if( id.startsWith('faqResposta') ) {
        object_id = id.split('_')[1];
        faqResposta(object_id);
    };
} );


