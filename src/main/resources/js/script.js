/**
 * Script JavaScript pour l'application de chat
 */


/* Copier le contenu d'un textarea dans le clipboard */
async function copyToClipboard(idTextArea) {
    var textArea = document.getElementById("form:" + idTextArea);
    if(textArea) {
        try {
            await navigator.clipboard.writeText(textArea.value);
        } catch (err) {
            console.error("Erreur lors de la copie : ", err)
        }
    }
    // Cet ancien code est deprecated :
    // textArea.select();
    // document.execCommand('copy');
}

/* Effacer la dernière question et la dernière réponse */
function toutEffacer() {
    document.getElementById("form:question").value = "";
    document.getElementById("form:reponse").value = "";
}


/**
 * Recherche un element JSF meme si son id HTML est prefixe (ex: form:question).
 * @param simpleId L'id logique sans prefixe
 * @returns {HTMLElement|null}
 */
function findElementBySimpleId(simpleId) {
    return document.getElementById(simpleId)
        || document.getElementById('form:' + simpleId)
        || document.querySelector('[id$="' + simpleId + '"]');
}



/**
 * Efface les zones Question et Reponse dans l'interface.
 */
function toutEffacer_michel() {
    const question = findElementBySimpleId('question');
    const reponse = findElementBySimpleId('reponse');

    if (question) {
        question.value = '';
    }

    if (reponse) {
        reponse.value = '';
    }
}

/**
 * Initialise les événements de la page
 */
document.addEventListener('DOMContentLoaded', function() {
    // Ajouter des raccourcis clavier
    document.addEventListener('keydown', function(event) {
        // Ctrl+Enter pour envoyer
        if (event.ctrlKey && event.key === 'Enter') {
            const sendBtn = document.querySelector('button[value="Envoyer"]');
            if (sendBtn) {
                sendBtn.click();
            }
        }
    });
});
