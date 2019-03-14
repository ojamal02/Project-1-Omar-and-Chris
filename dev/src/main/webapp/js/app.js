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
        let user = [];
        let resp = await response.json();
        user.push(resp.user_id);
        user.push(resp.role_id);
        createCookie("user", user, 5);
        console.log(readCookie("user"));
        let test = readCookie("user");
        console.log(test);

        let authorCookie = readCookie("user").split(",");
        if (authorCookie[1] === "1") {
            loadManagerDashboard();
        } else if (authorCookie[1] === "2") {
            loadDashboard();
        }

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
    // document.getElementById('register-username').addEventListener('blur', validateUsername);
    document.getElementById('first-name').addEventListener('blur', validateFirstName);
    document.getElementById('last-name').addEventListener('blur', validateLastName);
    document.getElementById('register-password').addEventListener('keyup', validatePassword);
    document.getElementById('register-account').addEventListener('click', register);
}

function validateFirstName(firstName) {

    if (firstName == "") {
        alert("You must enter a first name.")
        loadRegister();
    }

    console.log('in validateFirstName');
    console.log(event.target.value);
}

function validateLastName(lastName) {

    if (lastName == "") {
        alert("You must enter a last name.")
        loadRegister();
    }

    console.log('in validateLastName');
    console.log(event.target.value);
}

function validatePassword(event) {
    console.log('in validatePassword');
    console.log(event.target.value);
}

async function register() {
    console.log('in register()');

    validateFirstName(document.getElementById('first-name').value);
    validateLastName(document.getElementById('last-name').value);

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

    if (response.status == 200) {
        loadLogin();
    }
}

//-------------------------------------------------------------------------------------

/*
    Dashboard component
        - loadDashboard()
 */

async function loadDashboard() {
    console.log('in loadDashboard()');
    APP_VIEW.innerHTML = await fetchView('dashboard.view');
    configureDashboard();
    document.getElementById('to-submitReimb').addEventListener('click', loadSubmitReimbursement);
    document.getElementById('reimb-history').addEventListener('click', loadHistory);
    document.getElementById('contact-form').addEventListener('click', loadContact)
}

async function loadManagerDashboard() {
    console.log('In loadManagerDashboard()');
    APP_VIEW.innerHTML = await fetchView('managerdash.view');
    configureDashboard();
    document.getElementById('to-pending').addEventListener('click', loadPending);
    document.getElementById('view-approved').addEventListener('click', loadApprovals);
    document.getElementById('view-denied').addEventListener('click', loadDenials);
}

async function loadHistory() {
    console.log('In loadManagerHistory()');
    APP_VIEW.innerHTML = await fetchView('history.view');
}

async function loadApprovals() {
    console.log('In loadApprovals()');
    APP_VIEW.innerHTML = await fetchView('approvals.view');
}

async function loadContact() {
    console.log('In loadContact()');
    APP_VIEW.innerHTML = await fetchView('contact.view');
}

async function loadPending() {
    console.log('In loadPending()');
    APP_VIEW.innerHTML = await fetchView('pending.view');
    getReimbursements();
}

async function loadDenials() {
    console.log('In loadDenials()');
    APP_VIEW.innerHTML = await fetchView('denials.view');
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
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
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

function validateReimbAmount(reimbAmt) {
    if (reimbAmt <= 0) {
        alert("You must enter an amount greater than zero (0).")
        loadSubmitReimbursement();
    }
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

    validateReimbAmount(document.getElementById('reimb-amount').value);

    let authorCookie = readCookie("user").split(",");
    console.log(authorCookie);

    let newReimb = {
        reimbID: 0,
        reimbAmt: document.getElementById('reimb-amount').value,
        reimbTypeID: document.getElementById('reimb-type').value,
        reimbDesc: document.getElementById('description').value,
        reimbAuthor: authorCookie[0],
        reimbStatusID: 1
    };

    let response = await fetch('reimb', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json',
            // 'Authorization': localStorage.getItem('jwt')
        },
        body: JSON.stringify(newReimb)
    });

    let responseBody = await response.json();
    console.log(responseBody);
}

function createCookie(name, value, days) {

    let expires;
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toGMTString();
    } else expires = "";
    document.cookie = name + "=" + value + expires + "; path=/";
}

function readCookie(name) {
    let nameEQ = name + "=";
    let ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length, c.length);
    }
    return null;
}

function eraseCookie(name) {
    createCookie(name, "", -1);
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


async function getReimbursements() {
    let response = await fetch('approval', {
        method: 'GET',
        mode: 'cors',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });

    let x = await response.json();
    // createDisplayResults(x);

    let output = '';

    for (let i = 0; i < x.length; i++) {


        console.log(x[i].reimbID);
        console.log(x[i].reimbAmt);

        output +=
            '<tr>' +
            '<td style="width: 100px;">' + x[i].reimbID + '</td>' +
            '<td style="width: 100px;">' + x[i].reimbAuthor + '</td>' +
            '<td style="width: 100px;">' + x[i].reimbAmt + '</td>' +
            '<td style="width: 100px;">' + x[i].reimbDesc + '</td>' +
            '<td style="width: 100px;">' + x[i].reimbTypeID + '</td>' +
            '<td style="width: 100px;">' + x[i].reimbStatusID + '</td>' +
            '</tr>';

        document.getElementById("display-table").innerHTML = output;
    }




    // for (let i = 0; i < x.length; i++) {
    //     console.log(x[i].reimbId);
    // }
}

// var obj = { "1": 5, "2": 7, "3": 0, "4": 0, "5": 0, "6": 0, "7": 0, "8": 0, "9": 0, "10": 0, "11": 0, "12": 0 }
// var result = Object.keys(obj).map(function(key) {
//     return [Number(key), obj[key]];
// });

// console.log(result);

function createDisplayResults(x) {


}