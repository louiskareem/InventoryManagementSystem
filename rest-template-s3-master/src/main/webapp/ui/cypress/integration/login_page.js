describe("Login Page Test", function ()
{
    it("Employee tries to login", function ()
    {
        // Arrange
        cy.visit("http://localhost:3000/Login")
        cy.get('#inputEmail').type('').should('have.value', '')
        cy.get('#inputPassword').type('').should('have.value', '')

        // Act
        cy.contains('Login').click()
        cy.wait(10000)
        cy.get('.swal2-input').type('foobar').should('have.value', 'foobar')
        cy.get('.swal2-confirm').click()

        // Assert
        cy.url().should('include', '/Dashboard')
    })
})