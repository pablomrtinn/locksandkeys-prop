(define (domain locksandkeys)
  (:requirements :strips :equality :typing)
  (:types TCASELLA TAGENT TCLAU TPORTA)

  (:predicates
    (EsParet ?casella - TCASELLA)
    (TenimClau ?clau - TCLAU)
    (EsAgent ?agent - TAGENT ?casella - TCASELLA)
    (EsPorta ?casella - TCASELLA)
    (EstaBuida ?casella - TCASELLA)
    (EsSortida ?sortida - TCASELLA)
    (Veina ?casella1 ?casella2 - TCASELLA)
    (ClauObrePorta ?clau - TCLAU ?porta - TPORTA)
    (EsClau ?casella - TCASELLA)
  )

  (:action Moure
    :parameters (?agent - TAGENT ?deCasella ?aCasella - TCASELLA)
    :precondition (and 
      (or
        (EstaBuida ?aCasella)
        (EsSortida ?aCasella)
      )
      (EsAgent ?agent ?deCasella)
      (not (EsParet ?aCasella))
      (not (EsClau ?aCasella))
      (not (EsPorta ?aCasella))
      (not (EsAgent ?agent ?aCasella))
      (or 
        (Veina ?aCasella ?deCasella)
        (Veina ?deCasella ?aCasella)
      )
    )
    :effect (and 
      (not (EstaBuida ?aCasella))
      (EsAgent ?agent ?aCasella)
      (not (EsAgent ?agent ?deCasella))
    )
  )

  (:action MoureIAgafarClau
    :parameters (?agent - TAGENT ?deCasella ?aCasella - TCASELLA ?clau - TCLAU)
    :precondition (and 
      (EsClau ?aCasella)
      (EsAgent ?agent ?deCasella)
      (not (EstaBuida ?aCasella))
      (not (EsSortida ?aCasella))
      (not (EsParet ?aCasella)) 
      (not (EsPorta ?aCasella))
      (not (EsAgent ?agent ?aCasella))
	  ;(not(TenimClau ?clau))
      (or 
        (Veina ?aCasella ?deCasella)
        (Veina ?deCasella ?aCasella)
      )
    )
    :effect (and 
      (TenimClau ?clau)
      (EsAgent ?agent ?aCasella) 
      (not (EsAgent ?agent ?deCasella))
    )
  )

   (:action MoureIObrirPorta
    :parameters (?agent - TAGENT ?deCasella ?aCasella - TCASELLA ?clau - TCLAU ?porta - TPORTA)
    :precondition (and 
	  (TenimClau ?clau)
	  (ClauObrePorta ?clau ?porta)
	  (EsPorta ?aCasella)
      (not(EsClau ?aCasella))
      (EsAgent ?agent ?deCasella)
      (not (EstaBuida ?aCasella))
      (not (EsSortida ?aCasella))
      (not (EsParet ?aCasella)) 
      (not (EsAgent ?agent ?aCasella))
      (or 
        (Veina ?aCasella ?deCasella)
        (Veina ?deCasella ?aCasella)
      )
    )
    :effect (and
      (EsAgent ?agent ?aCasella) 
      (not (EsAgent ?agent ?deCasella))
    )
  )
)
