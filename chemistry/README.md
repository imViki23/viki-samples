# Chemistry

# Keywords

- **Intermolecular vs Intramolecular force**

## Quantum numbers

- **Principal Quantum Number (n)**
  - Defines the energy level (1, 2, 3…)
  - Larger `n` → higher energy and farther from the nucleus

- **Angular Momentum Quantum Number (l)**
  - Determines orbital type and shape
  - Possible values: `l = 0, 1, 2, …, n-1` (only possible values)
  - Orbital types:
    - `l = 0` → s orbital (1 orientation)
    - `l = 1` → p orbital (3 orientations)
    - `l = 2` → d orbital (5 orientations)
    - `l = 3` → f orbital (7 orientations)

- **Magnetic Quantum Number (mₗ)**
  - Specifies orbital orientation within a subshell
  - Range: `mₗ = -l … +l`

- **Spin Quantum Number (mₛ)**
  - Describes electron spin
  - Values: `+½` or `–½`
  - Ensures uniqueness of electron states (Pauli Exclusion Principle)

### Reference

https://www.youtube.com/watch?v=Aoi4j8es4gQ

### Maximum electrons per shell (2n²)

- **Formula:** $2n^2$ gives the maximum number of electrons that can occupy the shell with principal quantum number $n$.
- **Why:** A shell with principal quantum number $n$ contains $n^2$ orbitals in total (sum of subshell orbital counts). Each orbital holds up to 2 electrons (spin up and spin down), so maximum electrons = $2 \times n^2 = 2n^2$.
- **Examples:**
  - $n=1 \Rightarrow 2(1)^2 = 2$ electrons
  - $n=2 \Rightarrow 2(2)^2 = 8$ electrons
  - $n=3 \Rightarrow 2(3)^2 = 18$ electrons


## Electron configuration

### Rules
- **Aufbau principle:** Fill orbitals from lowest to highest energy
- **Hund's rule:** Fill orbitals singly before pairing
- **Pauli exclusion principle:** No two electrons share the same 4 quantum numbers

### Aufbau Diagram (Orbital Filling Order)

```
1s
2s  2p
3s  3p  4s
3d  4p  5s
4d  5p  6s
4f  5d  6p  7s
5f  6d  7p
```

👉 Read diagonally (1s → 2s → 2p → 3s → 3p → 4s → 3d → 4p → 5s …)

### 🧩 Examples of Electron Configurations

#### 1. Oxygen (Z = 8)
- **Configuration:** 1s² 2s² 2p⁴
- **Orbital diagram:**
  - 1s: ↑↓  
  - 2s: ↑↓  
  - 2p: ↑↓ ↑ ↑

#### 2. Chlorine (Z = 17)
- **Configuration:** 1s² 2s² 2p⁶ 3s² 3p⁵
- **Orbital diagram:**
  - 1s: ↑↓  
  - 2s: ↑↓  
  - 2p: ↑↓ ↑↓ ↑↓  
  - 3s: ↑↓  
  - 3p: ↑↓ ↑↓ ↑

#### 3. Iron (Z = 26)
- **Configuration:** 1s² 2s² 2p⁶ 3s² 3p⁶ 4s² 3d⁶
- **Orbital diagram:**
  - 3d: ↑↓ ↑ ↑ ↑ ↑ (Hund's rule applied)

### ✨ Quick Notes
- **Paramagnetic:** Atoms with unpaired electrons (e.g., O, Fe)
- **Diamagnetic:** All electrons paired (e.g., Ne)
- **Shortcut:** Use noble gas core notation  
  - Example: Cl = [Ne] 3s² 3p⁵

## Bonding & Electronegativity

### Electronegativity and bond types
- **Nonpolar covalent:** Δχ ≈ 0–0.4 (equal sharing)
- **Polar covalent:** Δχ ≈ 0.5–1.7 (unequal sharing)
- **Ionic:** Δχ > 1.7 (electron transfer)

Chart: see `./assets/electronegativity_chart.png`

### Bond types (summary)
| Type | Key properties | Examples |
|---|---:|---|
| Ionic | high mp/bp, lattice solids, conduct when molten/aq | NaCl |
| Polar covalent | dipole, hydrogen bonding possible | H₂O |
| Nonpolar covalent | low polarity, often gases/liquids | O₂, CH₄ |
| Metallic | delocalized electrons, conductive | Cu, Fe |

### Why ionic solids form lattices
- Ionic interactions are non-directional; cations and anions arrange in 3D lattices to maximize stabilization and lattice energy.

## Stoichiometry

### Mole & molar mass
- **Mole:** $6.022\times10^{23}$ particles (Avogadro's number)
- **Molar mass:** mass of one mole (g·mol⁻¹) — numerically equals atomic/molecular weight

Examples and visuals: see `./assets/mol_balancing.jpg` and `./assets/mol_balancing_2.png`

## Kinetic Molecular Theory & Gases

- Basic assumptions and behavior of ideal gases. See video: https://www.youtube.com/watch?v=F6lhYxV6gEY

Problems and variants: gas law visuals in `./assets/gas_law_variants.png`

## Lewis Diagrams & VSEPR

- Lewis dot structures: electron-pair representation for molecules (see linked video)
- VSEPR: molecular shapes predicted from electron pair repulsion (see `./assets/vsepr.png`)
