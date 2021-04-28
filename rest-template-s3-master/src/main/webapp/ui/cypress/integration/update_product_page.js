describe("Update Product Page Test", function ()
{
    it("Manager tries to update a product", function ()
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
        cy.get('td')
            .find('.btn.btn-outline-primary')
            .first()
            .click()
            .get('#name')
            .clear()
            .type('Playstation 5 Pro Digital')
            .get('.swal2-confirm')
            .click()
            .reload()
    })
})