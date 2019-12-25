package view;

public enum REnum {

    MELEE {
        @Override
        public String toString() {
            return "Melee Unit";
        }
    },
    RANGE {
        @Override
        public String toString() {
            return "Ranged Unit";
        }
    },
    HYBRID {
        @Override
        public String toString() {
            return "Hybrid Unit";
        }
    },
    SIEGE {
        @Override
        public String toString() {
            return "Siege Unit";
        }
    },
    SETTLER {
        @Override
        public String toString() {
            return "Settlers";
        }
    },
    FARMER {
        @Override
        public String toString() {
            return "Farmers";
        }
    },
    COALMINER {
        @Override
        public String toString() {
            return "Coal Miners";
        }
    },
    ANGLER {
        @Override
        public String toString() {
            return "Anglers";
        }
    },
    MASTERBUILDER {
        @Override
        public String toString() {
            return "Master Builders";
        }
    }
}
