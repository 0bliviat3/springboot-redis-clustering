async function sendData() {
    let id = $('#id').val();
    let pwd = $('#pwd').val();

    const loginData = {
            'id': id,
            'pwd': pwd
        };
    if (!validateInput(loginData)) {
        alert('empty!');
        return;
    }

    try {
        const response = await fetch(contextPath + 'sign/in', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        });

        if (response.ok) {
            // 로그인 성공 - 메인 페이지로 리다이렉트
            window.location.href = contextPath + 'home/main';  // 메인 페이지로 이동
        } else {
            // 로그인 실패 - 오류 메시지 표시
            const errorMessage = await response.text();
            alert(errorMessage);
        }
    } catch (error) {
        console.error('로그인 오류:', error);
        alert('서버 오류 발생');
    }
}

async function sendSignUpData() {
    let id = $('#id').val();
    let pwd = $('#pwd').val();
    let name = $("#name").val();

    const signUpData = {
        'id': id,
        'pwd': pwd,
        'name': name
    }

    if (!validateInput(signUpData)) {
        alert('empty!');
        return;
    }

    //TODO: 회원가입 요청
    try {
        const response = await fetch(contextPath + 'sign/up', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(signUpData)
        });

        if (response.ok) {
            // 로그인 성공 - 메인 페이지로 리다이렉트
            window.location.href = contextPath + 'sign/in-page';  // 메인 페이지로 이동
        } else {
            // 로그인 실패 - 오류 메시지 표시
            const errorMessage = await response.text();
            alert(errorMessage);
        }
    } catch (error) {
        console.error('로그인 오류:', error);
        alert('서버 오류 발생');
    }
}

function validateInput(obj) {
    for (key in obj) {
        let val = obj[key];
        if (!val || val == '') {
            return false;
        }
    }
    return true;
}