describe("Search Inventory Test", function ()
{
    it("Manager tries to search the inventory", function ()
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
        cy.url().should('include', '/Dashboard')

        // Assert
        cy.get('#searchInput').type('makita').should('have.value', 'makita') // Every data with the word makita will be displayed in the table
    })
})