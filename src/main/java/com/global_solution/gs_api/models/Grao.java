package com.global_solution.gs_api.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "TB_GRAO")
public class Grao {

    public Grao(String NM_GRAO, String DS_GRAO) {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID_GRAO;
    @NotNull
    @Size(max = 255)
    private String NM_GRAO;
    @NotNull
    @Size(max = 255)
    private String DS_GRAO;
    @OneToMany(mappedBy = "grao")
    private List<Cultivos> cultivos;

}
