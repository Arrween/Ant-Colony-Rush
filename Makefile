# Nom du compilateur Java
JC = javac

# Nom de l'interpréteur Java
JR = java

# Options de compilation (par exemple -g pour inclure les infos de debug)
JFLAGS = -g

# Répertoires source et binaire
SRCDIR = src
BINDIR = bin

# Recherche tous les .java dans le répertoire src
SOURCES = $(wildcard $(SRCDIR)/*.java)
# Transforme la liste de .java en .class dans bin/
CLASSES = $(SOURCES:$(SRCDIR)/%.java=$(BINDIR)/%.class)

# Règle par défaut
default: classes

# Cible "classes" : compile toutes les classes dans bin/
classes: $(CLASSES)

# Règle de compilation : transforme un fichier .java (dans src) 
# en .class (dans bin), en créant le dossier bin si besoin
$(BINDIR)/%.class: $(SRCDIR)/%.java
	@mkdir -p $(BINDIR)
	$(JC) $(JFLAGS) -d $(BINDIR) $<

# Exécution (on suppose que la classe Main contient la méthode main)
run: classes
	$(JR) -cp $(BINDIR) Main

# Nettoyage : supprime tous les .class générés
clean:
	rm -rf $(BINDIR)
