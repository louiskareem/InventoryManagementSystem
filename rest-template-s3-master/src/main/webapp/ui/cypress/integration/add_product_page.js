describe("Add Product Page Test", function ()
{
    it("Manager tries to add a product", function ()
    {
        // Arrange
        cy.visit("http://localhost:3000/Login")
        cy.get('#inputEmail').type('').should('have.value', '')
        cy.get('#inputPassword').type('').should('have.value', '')
        cy.contains('Login').click()
        cy.wait(10000)
        cy.get('.swal2-input').type('foobar').should('have.value', 'foobar')
        cy.get('.swal2-confirm').click()
        cy.url().should('include', '/Dashboard')

        // Act
        cy.contains('Products').click()

        // Assert
        cy.get('.btn-outline-info')
            .click()
            .get('#inputName')
            .type('call of duty')
            .get('#inputDescription')
            .type('cold war')
            .get('#inputPrice')
            .type('70.00')
            .get('form').submit()
            .reload()
        cy.contains('Products').click()
    })
})