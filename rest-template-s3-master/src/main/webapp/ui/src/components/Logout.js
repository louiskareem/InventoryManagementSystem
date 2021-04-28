const Logout = () =>
{
    sessionStorage.removeItem('loggedIn')
    sessionStorage.removeItem('Base')
    console.log("you logged out");
    window.location.href="/Login"
}

export default Logout;