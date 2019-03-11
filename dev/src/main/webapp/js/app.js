window.onload = function() {
    document.getElementById('to-login').addEventListener('click', loadLogin);
    document.getElementById('to-register').addEventListener('click', loadRegister);

};

/*
    Login component

        - loadLogin()
        - configureLogin()
        - login()
*/
async function loadLogin() {
    console.log('in loadLogin()');

    // fetchView('login.view').then(view => {
    //     APP_VIEW.innerHTML = view;
    //     DYNAMIC_CSS_LINK.href = 'css/login.css';
    //     configureLogin();
    // });

    APP_VIEW.innerHTML = await fetchView('login.view');
    //DYNAMIC_CSS_LINK.href = 'css/login.css';
    configureLogin();
}

function configureLogin() {
    console.log('in configureLogin()');
    //document.getElementById('alert-msg').hidden = true;
    document.getElementById('loginButton').addEventListener('click', login);
}

async function login() {
    console.log('in login()');
    let credentials = [];
    credentials.push(document.getElementById('username').value);
    credentials.push(document.getElementById('password').value);

    console.log(credentials);

    let response = await fetch('auth', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(credentials)
    });

    if (response.status == 200) {
        console.log()
            // document.getElementById('alert-msg').hidden = true;
        localStorage.setItem('jwt', response.headers.get('Authorization'));
        loadDashboard();
    } else {
        document.getElementById('alert-msg').hidden = false;
    }

}

//-------------------------------------------------------------------------------------

/*
    Register component

        - loadRegister()
        - configureRegister()
        - validateUsername()
        - validatePassword()
        - register()
*/

async function loadRegister() {
    console.log('in loadRegister()');
    APP_VIEW.innerHTML = await fetchView('register.view');
    // DYNAMIC_CSS_LINK.href = 'css/register.css';
    configureRegister();
}

function configureRegister() {
    console.log('in configureRegister()');
    document.getElementById('register-username').addEventListener('blur', validateUsername);
    document.getElementById('register-password').addEventListener('keyup', validatePassword);
    document.getElementById('register-account').addEventListener('click', register);
}

function validateUsername(event) {
    console.log('in validateUsername');
    console.log(event.target.value);
}

function validatePassword(event) {
    console.log('in validatePassword');
    console.log(event.target.value);
}

async function register() {
    console.log('in register()');

    let newUser = {
        user_id: 0,
        username: document.getElementById('register-username').value,
        password: document.getElementById('register-password').value,
        firstName: document.getElementById('first-name').value,
        lastName: document.getElementById('last-name').value,
        email: document.getElementById('email').value,
        role_id: 2
    };

    let response = await fetch('users', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    });

    let responseBody = await response.json();
    console.log(responseBody);
}

//-------------------------------------------------------------------------------------

/*
    Dashboard component
        - loadDashboard()
 */

async function loadDashboard() {
    console.log('in loadDashboard()');
    APP_VIEW.innerHTML = await fetchView('dashboard.view');
    //DYNAMIC_CSS_LINK.href = 'css/dashboard.css';
    configureDashboard();
    document.getElementById('to-submitReimb').addEventListener('click', loadSubmitReimbursement);
}

function configureDashboard() {
    console.log('in configureDashboard()');
}

//-------------------------------------------------------------------------------------
async function fetchView(uri) {
    let response = await fetch(uri, {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Authorization': localStorage.getItem('jwt')
        }
    });

    if (response.status == 401) loadLogin();
    return await response.text();
}

//-------------------------------------------------------------------------------------

// Submit Reimbursement Functions

async function loadSubmitReimbursement() {
    console.log('in loadSubmitReimbursement()');
    APP_VIEW.innerHTML = await fetchView('submit_reimbursement.view');
    // DYNAMIC_CSS_LINK.href = 'css/register.css';
    configureSubmitReimbursement();
}

function configureSubmitReimbursement() {
    console.log('in configureSubmitReimbursement()');
    document.getElementById('reimb-amount').addEventListener('blur', validateReimbAmount);
    document.getElementById('reimb-type').addEventListener('keyup', validateReimbType);
    document.getElementById('description').addEventListener('keyup', validateDescription);
    document.getElementById('submit-reimb').addEventListener('click', reimbSubmit);
}

function validateReimbAmount(event) {
    console.log('in validateReimbAmount');
    console.log(event.target.value);
}

function validateReimbType(event) {
    console.log('in validateReimbType');
    console.log(event.target.value);
}

function validateDescription(event) {
    console.log('in validateDescription');
    console.log(event.target.value);
}

async function reimbSubmit() {
    console.log('in reimbSubmit()');

    let newReimb = {
        reimbID: 0,
        reimbAmt: document.getElementById('reimb-amount').value,
        reimbTypeID: document.getElementById('reimb-type').value,
        reimbDesc: document.getElementById('description').value,
        reimbStatusID: 1
    };

    let response = await fetch('reimb', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newReimb)
    });

    let responseBody = await response.json();
    console.log(responseBody);
}

const APP_VIEW = document.getElementById('app-view');
const DYNAMIC_CSS_LINK = document.getElementById('dynamic-css');

// Display Reimbursement History

// async function loadReimbHistory() {
//     console.log('in loadReimbHistory()');
//     APP_VIEW.innerHTML = await fetchView('history.view');
//     // DYNAMIC_CSS_LINK.href = 'css/register.css';
//     configureReimbHistory();
// }

// function configureReimbHistory() {

//     console.log('in configureReimbHistory()');
//     document.getElementById('reimb-amount').addEventListener('blur', validateReimbAmount);
//     document.getElementById('reimb-type').addEventListener('keyup', validateReimbType);
//     document.getElementById('description').addEventListener('keyup', validateDescription);
//     document.getElementById('submit-reimb').addEventListener('click', reimbSubmit);
// }